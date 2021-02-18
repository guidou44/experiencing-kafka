package com.messagebroker.infrastructure.exception;

public class TopicException extends RuntimeException {
    public TopicException(String message) {
        super(TopicException.class.getName() + System.lineSeparator() + message);
    }
}
