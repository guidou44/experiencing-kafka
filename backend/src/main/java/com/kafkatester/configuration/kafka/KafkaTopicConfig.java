package com.kafkatester.configuration.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

public class KafkaTopicConfig {

    @Value("${spring.kafka.topic-test}")
    private String testTopic;
    @Value("${spring.kafka.topic-error}")
    private String errorTopic;
    @Value("${spring.kafka.topic-network}")
    private String networkTopic;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    NewTopic testTopic() {
        return TopicBuilder.name(testTopic).build();
    }

    @Bean
    NewTopic errorTopic() {
        return TopicBuilder.name(errorTopic).build();
    }

    @Bean
    NewTopic networkTopic() {
        return TopicBuilder.name(networkTopic).build();
    }

}
