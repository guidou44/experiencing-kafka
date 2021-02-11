package com.kafkatester.infrastructure.kafka;

import com.kafkatester.domain.messagebroker.MessageSender;
import com.kafkatester.infrastructure.kafka.TopicRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender<V> implements MessageSender<V> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(V message) {
        String topic = TopicRouter.getTopicRouting(message);
        kafkaTemplate.send(topic, message);
    }
}
