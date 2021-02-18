package com.messagebroker.infrastructure.producers;

import com.messagebroker.domain.messages.ErrorMessage;
import com.messagebroker.infrastructure.base.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ErrorProducer extends KafkaProducer<ErrorMessage> {

    @Value("${spring.kafka.topic-error}")
    private String topic;

    @Autowired
    public ErrorProducer(KafkaTemplate<String, ErrorMessage> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void sendMessage(ErrorMessage message) {
        kafkaTemplate.send(topic, message);
    }
}
