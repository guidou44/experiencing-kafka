package com.kafkatester.infrastructure.kafka;

import com.kafkatester.domain.messages.TestMessage;
import com.kafkatester.infrastructure.kafka.base.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestSender extends KafkaSender<TestMessage> {

    @Autowired
    public TestSender(KafkaTemplate<String, TestMessage> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void sendMessage(TestMessage message) {
        String topic = TopicRouter.getTopicRouting(message);
        kafkaTemplate.send(topic, message);
    }
}