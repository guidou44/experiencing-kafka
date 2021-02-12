package com.kafkatester.infrastructure.kafka.base;

import com.kafkatester.domain.messagebroker.MessageSender;
import com.kafkatester.domain.messages.TestMessage;
import com.kafkatester.infrastructure.kafka.TopicRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

public abstract class KafkaSender<V> implements MessageSender<V> {

    protected final KafkaTemplate<String, TestMessage> kafkaTemplate;
    protected final TopicRouter router;

    public KafkaSender(KafkaTemplate<String, TestMessage> kafkaTemplate, TopicRouter router) {
        this.kafkaTemplate = kafkaTemplate;
        this.router = router;
    }
}
