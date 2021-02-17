package com.networkscanner.domain;

public interface MessageProducer<TValue> {
    void sendMessage(TValue message);
}
