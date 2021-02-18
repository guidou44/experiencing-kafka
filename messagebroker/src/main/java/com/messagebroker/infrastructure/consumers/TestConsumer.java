package com.messagebroker.infrastructure.consumers;

import com.messagebroker.domain.messages.TestMessage;
import com.messagebroker.infrastructure.base.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer extends KafkaConsumer<TestMessage> {

    @Override
    @KafkaListener(topics = "#{'${spring.kafka.topic-test}'}")
    public void listen(TestMessage message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        this.messageQueue.add(message);
    }
}
