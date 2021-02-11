package com.kafkatester.domain.messagebroker;

import java.util.Optional;

public interface MessageListener<TValue> {
    void listen(TValue message);
    Optional<TValue> dequeue();
}
