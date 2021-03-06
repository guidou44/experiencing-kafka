package com.kafkatester.configuration.kafka;


import com.kafkatester.domain.messages.ErrorMessage;
import com.kafkatester.domain.messages.NetworkScanMessage;
import com.kafkatester.domain.messages.TestMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    //region test message
    @Bean
    public ProducerFactory<String, TestMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, TestMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    //endregion

    //region error message
    @Bean
    public ProducerFactory<String, ErrorMessage> errorProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, ErrorMessage> errorKafkaTemplate() {
        return new KafkaTemplate<>(errorProducerFactory());
    }
    //endregion

    //region network scan message
    @Bean
    public ProducerFactory<String, NetworkScanMessage> networkScanProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, NetworkScanMessage> networkScanKafkaTemplate() {
        return new KafkaTemplate<>(networkScanProducerFactory());
    }
    //endregion
}