package com.messagebroker.configuration;

import com.messagebroker.domain.messages.ErrorMessage;
import com.messagebroker.domain.messages.NetworkScanMessage;
import com.messagebroker.domain.messages.TestMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.group-id}")
    private String groupId;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    //region test message
    private ConsumerFactory<String, TestMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(TestMessage.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, TestMessage>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TestMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    //endregion

    //region error message
    private ConsumerFactory<String, ErrorMessage> errorMessageConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(ErrorMessage.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ErrorMessage>> kafkaListenerErrorMessageContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ErrorMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(errorMessageConsumerFactory());
        return factory;
    }
    //endregion

    //region network scan message
    private ConsumerFactory<String, NetworkScanMessage> networkScanConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(NetworkScanMessage.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, NetworkScanMessage>> kafkaListenerNetworkScanContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NetworkScanMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(networkScanConsumerFactory());
        return factory;
    }
    //endregion
}
