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

    @Override
    @KafkaListener(topics = "#{'${spring.kafka.topic-network}'}")
    public void listen(@Payload NetworkScanMessage message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        this.messageQueue.add(message);
    }
}
