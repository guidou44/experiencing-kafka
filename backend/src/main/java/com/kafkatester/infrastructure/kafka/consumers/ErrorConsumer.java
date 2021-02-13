package com.kafkatester.infrastructure.kafka.consumers;

import com.kafkatester.domain.messages.ErrorMessage;
import com.kafkatester.infrastructure.kafka.base.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

public class ErrorConsumer extends KafkaConsumer<ErrorMessage> {

    @Override
    @KafkaListener(topics = "#{'${spring.kafka.topic-error}'}")
    public void listen(ErrorMessage message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        this.messageQueue.add(message);
    }
}
