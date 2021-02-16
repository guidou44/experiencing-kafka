package com.kafkatester.domain.network;

import com.kafkatester.domain.messagebroker.MessageProducer;
import com.kafkatester.domain.messages.ErrorMessage;
import com.kafkatester.domain.messages.NetworkScanMessage;
import com.kafkatester.domain.network.exceptions.VolunteerChaosException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
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

    @Autowired
    public NetworkScanner(MessageProducer<ErrorMessage> errorProducer, MessageProducer<NetworkScanMessage> networkScanProducer) {
        this.errorProducer = errorProducer;
        this.networkScanProducer = networkScanProducer;
    }

    @Override
    public void run() {

        try {
            while (continueScan.get()) {
                try {
                    int randomDeviceAddress = ThreadLocalRandom.current().nextInt(MIN_DEVICE_ADDRESS, MAX_DEVICE_ADDRESS);
                    String hostIp = InetAddress.getLocalHost().getHostAddress();
                    String[] addressComponents = hostIp.split("\\.");
                    addressComponents[addressComponents.length - 1] = Integer.toString(randomDeviceAddress);
                    InetAddress address = InetAddress.getByName(String.join(".", addressComponents));
                    if (randomDeviceAddress % 7 == 0) {
                        throw new VolunteerChaosException("testing error topic for " + address.getHostAddress(), address.getHostAddress());
                    }

                    long duration = -1;
                    if (randomDeviceAddress % 2 == 0) {
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
                        }
                        NetworkScanMessage netScan = new NetworkScanMessage(address.getHostAddress(), address.getHostName(), duration, reachable);
                        networkScanProducer.sendMessage(netScan);
                    }
                    Thread.sleep(LOOP_SLEEP_MILLIS);
                } catch (VolunteerChaosException ex) {
                    ErrorMessage err = new ErrorMessage(ex, ex.getIpAddress());
                    errorProducer.sendMessage(err);
                }
            }
        } catch (Exception ex) {
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
}
