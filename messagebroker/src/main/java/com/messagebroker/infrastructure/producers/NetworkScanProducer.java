package com.messagebroker.infrastructure.producers;

import com.messagebroker.domain.messages.NetworkScanMessage;
import com.messagebroker.infrastructure.base.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NetworkScanProducer extends KafkaProducer<NetworkScanMessage> {

    @Value("${spring.kafka.topic-network}")
    private String topic;

    public NetworkScanProducer(KafkaTemplate<String, NetworkScanMessage> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void sendMessage(NetworkScanMessage message) {
        kafkaTemplate.send(topic, message);
    }
}
