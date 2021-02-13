package com.kafkatester.domain.messagebroker;

public interface MessageProducer<TValue> {
    void sendMessage(TValue message);
}
