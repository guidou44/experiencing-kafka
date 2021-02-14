package com.kafkatester.infrastructure.kafka.consumers;

import com.kafkatester.domain.messages.NetworkScanMessage;
import com.kafkatester.infrastructure.kafka.base.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NetworkScanConsumer extends KafkaConsumer<NetworkScanMessage> {

    private static final int MAX_QUEUE_SIZE = 100;

    @Override
    @KafkaListener(topics = "#{'${spring.kafka.topic-network}'}")
    public void listen(@Payload NetworkScanMessage message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        if (messageQueue.size() >= MAX_QUEUE_SIZE) {
            messageQueue.poll();
        }
        this.messageQueue.add(message);
    }
}
