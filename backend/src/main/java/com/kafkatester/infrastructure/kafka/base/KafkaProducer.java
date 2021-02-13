package com.kafkatester.infrastructure.kafka.base;

import com.kafkatester.domain.messagebroker.MessageProducer;
import com.kafkatester.domain.messages.TestMessage;
import org.springframework.kafka.core.KafkaTemplate;

public abstract class KafkaProducer<V> implements MessageProducer<V> {

    protected final KafkaTemplate<String, V> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
