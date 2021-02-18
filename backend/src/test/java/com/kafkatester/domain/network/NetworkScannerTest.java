package com.kafkatester.domain.network;

import com.kafkatester.domain.messagebroker.MessageProducer;
import com.kafkatester.domain.messages.ErrorMessage;
import com.kafkatester.domain.messages.NetworkScanMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NetworkScannerTest {

    @Mock
    MessageProducer<ErrorMessage> errorProducerMock;
    @Mock
    MessageProducer<NetworkScanMessage> networkScanProducerMock;

    private NetworkScanner scanner;

    @Before
    public void setup() {
        scanner = new NetworkScanner(errorProducerMock, networkScanProducerMock);
        //ReflectionTestUtils.setField(scanner, "hostIp", "127.0.0.1");
    }

    @Test
    public void givenAddress_whenPingWithoutError_thenItProduceProperMessageToBroker() throws InterruptedException {
        Thread worker = new Thread(scanner);
        worker.start();
        while (scanner.getLoopCount() == 0) {
            Thread.sleep(50);
        }
        scanner.destroy();

        verify(networkScanProducerMock).sendMessage(any(NetworkScanMessage.class));
    }

    @Test
    public void givenInvalidAddress_whenPingWithError_thenItProduceProperMessageToBroker() throws InterruptedException {
        doThrow(new RuntimeException()).when(networkScanProducerMock).sendMessage(any(NetworkScanMessage.class));
        Thread worker = new Thread(scanner);
        worker.start();
        while (scanner.getLoopCount() == 0) {
            Thread.sleep(50);
        }
        scanner.destroy();

        verify(errorProducerMock).sendMessage(any(ErrorMessage.class));
    }
}
