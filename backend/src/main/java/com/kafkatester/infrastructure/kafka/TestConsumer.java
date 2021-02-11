package com.kafkatester.infrastructure.kafka;

import com.kafkatester.domain.messages.TestMessage;
import com.kafkatester.infrastructure.kafka.base.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer extends KafkaConsumer<TestMessage> {

    @Override
    @KafkaListener(topics = "#{'${spring.kafka.topic-test}'}")
    public void listen(TestMessage message) {

    }
}
