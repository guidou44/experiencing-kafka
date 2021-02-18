package com.messagebroker.infrastructure.consumers;

import com.messagebroker.domain.messages.ErrorMessage;
import com.messagebroker.infrastructure.base.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class ErrorConsumer extends KafkaConsumer<ErrorMessage> {

    @Override
    @KafkaListener(topics = "#{'${spring.kafka.topic-error}'}")
    public void listen(ErrorMessage message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        this.messageQueue.add(message);
    }
}
