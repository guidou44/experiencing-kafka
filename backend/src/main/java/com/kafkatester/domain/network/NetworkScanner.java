package com.kafkatester.domain.network;

import com.kafkatester.domain.messagebroker.MessageProducer;
import com.kafkatester.domain.messages.ErrorMessage;
import com.kafkatester.domain.messages.NetworkScanMessage;
import com.kafkatester.domain.network.exceptions.VolunteerChaosException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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
    private static final int PING_TIMEOUT = 3000;

    private final MessageProducer<ErrorMessage> errorProducer;
    private final MessageProducer<NetworkScanMessage> networkScanProducer;


    private final AtomicBoolean continueScan = new AtomicBoolean(true);
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private long loopCount = 0;
    private long successfulCount = 0;

    @Autowired
    public NetworkScanner(MessageProducer<ErrorMessage> errorProducer, MessageProducer<NetworkScanMessage> networkScanProducer) {
        this.errorProducer = errorProducer;
        this.networkScanProducer = networkScanProducer;
    }

    @Override
    public void run() {

        try {
            String hostIp = getLocalHostIp();
            while (continueScan.get()) {
                try {
                    int randomDeviceAddress = ThreadLocalRandom.current().nextInt(MIN_DEVICE_ADDRESS, MAX_DEVICE_ADDRESS);
                    InetAddress address = getRandomizedAddress(hostIp, randomDeviceAddress);

                    long duration = -1;
                    if (randomDeviceAddress % 2 == 0 && cannotReachAnyHost()) { //fake success
                        Instant startTime = Instant.now();
                        boolean reachable = true;
                        duration = Duration.between(startTime, Instant.now()).toMillis();
                        NetworkScanMessage netScan = new NetworkScanMessage(address.getHostAddress(), address.getHostName(), duration, reachable);
                        networkScanProducer.sendMessage(netScan);
                    } else {
                        Instant startTime = Instant.now();
                        boolean reachable = address.isReachable(PING_TIMEOUT);
                        if (reachable) {
                            duration = Duration.between(startTime, Instant.now()).toMillis();
                            successfulCount++;
                        }
                        NetworkScanMessage netScan = new NetworkScanMessage(address.getHostAddress(), address.getHostName(), duration, reachable);
                        networkScanProducer.sendMessage(netScan);
                    }
                    loopCount++;
                    Thread.sleep(LOOP_SLEEP_MILLIS);
                } catch (VolunteerChaosException ex) {
                    ErrorMessage err = new ErrorMessage(ex, ex.getIpAddress());
                    errorProducer.sendMessage(err);
                }
            }
        } catch (Exception ex) {
            loopCount = -1;
            ErrorMessage err = new ErrorMessage(ex, "");
            errorProducer.sendMessage(err);
        } finally {
            countDownLatch.countDown();
        }
    }

    private String getLocalHostIp() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80));
        String hostSocketIp = socket.getLocalAddress().getHostAddress();
        socket.close();
        return hostSocketIp;
    }

    private InetAddress getRandomizedAddress(String hostIp, int randomDeviceAddress) throws UnknownHostException {
        String[] addressComponents = hostIp.split("\\.");
        addressComponents[addressComponents.length - 1] = Integer.toString(randomDeviceAddress);
        InetAddress address = InetAddress.getByName(String.join(".", addressComponents));
        if (randomDeviceAddress % 7 == 0) {
            throw new VolunteerChaosException("testing error topic for " + address.getHostAddress(), address.getHostAddress());
        }
        return address;
    }

    private boolean cannotReachAnyHost() {
        return loopCount > 30 && successfulCount == 0;
    }

    public long getLoopCount() {
        return loopCount;
    }

    @Override
    public void destroy() throws InterruptedException {
        continueScan.set(false);
        countDownLatch.await();
    }
}
