package com.messagebroker.infrastructure.base;

import com.messagebroker.domain.MessageProducer;
import org.springframework.kafka.core.KafkaTemplate;

public abstract class KafkaProducer<V> implements MessageProducer<V> {

    protected final KafkaTemplate<String, V> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
