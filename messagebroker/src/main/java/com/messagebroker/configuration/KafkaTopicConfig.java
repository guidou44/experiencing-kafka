package com.messagebroker.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

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
    NewTopic errorTopic() {
        return TopicBuilder.name(errorTopic).build();
    }

    @Bean
    NewTopic networkTopic() {
        return TopicBuilder.name(networkTopic).build();
    }

}
