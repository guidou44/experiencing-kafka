package com.networkscanner.scanner;

import com.messagebroker.domain.MessageProducer;
import com.messagebroker.domain.messages.ErrorMessage;
import com.messagebroker.domain.messages.NetworkScanMessage;
import com.networkscanner.scanner.exceptions.VolunteerChaosException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class NetworkScanner implements Runnable, DisposableBean {

    private static final int MIN_DEVICE_ADDRESS = 1;
    private static final int MAX_DEVICE_ADDRESS = 255;
    private static final int LOOP_SLEEP_MILLIS = 3000;
    private static final int PING_TIMEOUT = 1000;

    private final MessageProducer<ErrorMessage> errorProducer;
    private final MessageProducer<NetworkScanMessage> networkScanProducer;


    private final AtomicBoolean continueScan = new AtomicBoolean(true);
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private long loopCount = 0;

    @Autowired
    public NetworkScanner(MessageProducer<ErrorMessage> errorProducer,
                          MessageProducer<NetworkScanMessage> networkScanProducer) {
        this.errorProducer = errorProducer;
        this.networkScanProducer = networkScanProducer;
    }

    @Override
    public void run() {

        try {
            String hostIp = getLocalHostIp();
            System.out.println("HOST IP : hostIp");

            while (continueScan.get()) {
                try {
                    InetAddress address = randomizeIpAddress(hostIp);
                    long duration = -1;
                    Instant startTime = Instant.now();
                    System.out.println("TRYING : " + address.getHostAddress());
                    boolean reachable = address.isReachable(PING_TIMEOUT);
                    if (reachable) {
                        duration = Duration.between(startTime, Instant.now()).toMillis();
                    }
                    NetworkScanMessage netScan = new NetworkScanMessage(address.getHostAddress(), address.getHostName(), duration, reachable);
                    System.out.println("SENDING TO BROKER");
                    networkScanProducer.sendMessage(netScan);
                    Thread.sleep(LOOP_SLEEP_MILLIS);
                } catch (VolunteerChaosException ex) {
                    ErrorMessage err = new ErrorMessage(ex, ex.getIpAddress());
                    errorProducer.sendMessage(err);
                }
                loopCount++;
            }
        } catch (Exception ex) {
            loopCount = -1;
            ErrorMessage err = new ErrorMessage(ex, "");
            errorProducer.sendMessage(err);
        } finally {
            countDownLatch.countDown();
        }
    }

    @Override
    public void destroy() throws InterruptedException {
        continueScan.set(false);
        countDownLatch.await();
    }

    public long getLoopCount() {
        return loopCount;
    }

    private String getLocalHostIp() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80));
        String hostSocketIp = socket.getLocalAddress().getHostAddress();
        socket.close();
        return hostSocketIp;
    }

    private InetAddress randomizeIpAddress(String hostIp) throws UnknownHostException {
        int randomDeviceAddress = ThreadLocalRandom.current().nextInt(MIN_DEVICE_ADDRESS, MAX_DEVICE_ADDRESS);
        String[] addressComponents = hostIp.split("\\.");
        addressComponents[addressComponents.length - 1] = Integer.toString(randomDeviceAddress);
        InetAddress address = InetAddress.getByName(String.join(".", addressComponents));
        if (randomDeviceAddress % 7 == 0) {
            throw new VolunteerChaosException("testing error topic for " + address.getHostAddress(), address.getHostAddress());
        }
        return address;
    }

}
