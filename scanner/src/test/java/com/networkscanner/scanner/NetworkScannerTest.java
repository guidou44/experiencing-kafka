package com.networkscanner.scanner;

import com.messagebroker.domain.MessageProducer;
import com.messagebroker.domain.messages.ErrorMessage;
import com.messagebroker.domain.messages.NetworkScanMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NetworkScannerTest {

    @Mock
    MessageProducer<ErrorMessage> errorProducerMock;
    @Mock
    MessageProducer<NetworkScanMessage> networkScanProducerMock;

    private NetworkScanner scanner;

    @BeforeAll
    public void setup() {
        scanner = new NetworkScanner(errorProducerMock, networkScanProducerMock);
        //ReflectionTestUtils.setField(scanner, "hostIp", "127.0.0.1");
    }

    @Test
    public void givenAddress_whenPingWithoutError_thenItProduceProperMessageToBroker() throws InterruptedException {
        Thread worker = new Thread(scanner);
        worker.start();
        Thread.sleep(500);
        scanner.destroy();

        verify(networkScanProducerMock).sendMessage(any(NetworkScanMessage.class));
    }

    @Test
    public void givenInvalidAddress_whenPingWithError_thenItProduceProperMessageToBroker() throws InterruptedException {
        doThrow(new RuntimeException()).when(networkScanProducerMock).sendMessage(any(NetworkScanMessage.class));
        Thread worker = new Thread(scanner);
        worker.start();
        Thread.sleep(500);
        scanner.destroy();

        verify(errorProducerMock).sendMessage(any(ErrorMessage.class));
    }
}
