package com.kafkatester.domain.messagebroker;

public interface MessageSender<TValue> {
    void sendMessage(TValue message);
}
