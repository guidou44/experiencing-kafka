package com.networkscanner.scanner;

import com.messagebroker.domain.MessageProducer;
import com.messagebroker.domain.messages.ErrorMessage;
import com.messagebroker.domain.messages.NetworkScanMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NetworkScannerTest {

    @Mock
    MessageProducer<ErrorMessage> errorProducerMock;
    @Mock
    MessageProducer<NetworkScanMessage> networkScanProducerMock;

    private NetworkScanner scanner;

    @BeforeEach
    public void setUp() {
        scanner = new NetworkScanner(errorProducerMock, networkScanProducerMock);
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
