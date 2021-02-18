package com.messagebroker.domain;

import java.util.Optional;

public interface MessageListener<TValue> {
    void listen(TValue message, int partition);
    Optional<TValue> dequeue();
}
