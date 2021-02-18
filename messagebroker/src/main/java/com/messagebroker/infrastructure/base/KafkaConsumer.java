package com.messagebroker.infrastructure.base;

import com.messagebroker.domain.MessageListener;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public abstract class KafkaConsumer<V> implements MessageListener<V> {

    protected Queue<V> messageQueue =  new LinkedList<>();

    @Override
    public Optional<V> dequeue() {
        return Optional.ofNullable(messageQueue.poll());
    }
}
