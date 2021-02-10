package com.kafkatester.domain;

import java.util.List;

public interface IMessageBroker<V> {
    void start();
    V consumeLatestRecord();
    List<V> ConsumeAllRecords();
}
