package com.messagebroker.domain;

public interface MessageProducer<TValue> {
    void sendMessage(TValue message);
}
