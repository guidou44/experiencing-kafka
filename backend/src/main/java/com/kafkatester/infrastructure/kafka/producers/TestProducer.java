package com.kafkatester.infrastructure.kafka.producers;

import com.kafkatester.domain.messages.TestMessage;
import com.kafkatester.infrastructure.kafka.base.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestProducer extends KafkaProducer<TestMessage> {

    @Value("${spring.kafka.topic-test}")
    private String testTopic;

    @Autowired
    public TestProducer(KafkaTemplate<String, TestMessage> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void sendMessage(TestMessage message) {
        kafkaTemplate.send(testTopic, message);
    }
}
