package com.kafkatester.infrastructure.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.List;

public class KafkaClient<K, V> implements Runnable {

    private final KafkaConsumer<K, V> consumer;
    private final List<String> topic;

    public KafkaClient(KafkaConsumer<K, V> consumer, List<String> topics) {
        this.consumer = consumer;
        this.topic = topics;
    }


    @Override
    public void run() {

    }
}
