package com.kafkatester.infrastructure.kafka;

import com.kafkatester.domain.messages.TestMessage;
import com.kafkatester.infrastructure.kafka.exception.TopicException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TopicRouter {

    @Value("${spring.kafka.topic-test}")
    private String testTopic;

    private static Map<String, List<Class<?>>> topicMessageMapping = new HashMap<>();

    public void initialize() {
        if (testTopic != null && !topicMessageMapping.containsKey(testTopic))
            topicMessageMapping.put(testTopic, Collections.singletonList(TestMessage.class));
    }

    public String getTopicRouting(Object messageContent) {
        initialize();

        for (String topic : topicMessageMapping.keySet()) {
            if (topicMessageMapping.get(topic).contains(messageContent.getClass())) {
                return topic;
            }
        }

        throw new TopicException("No topic found for object of type " + messageContent.getClass().getName());
    }
}
