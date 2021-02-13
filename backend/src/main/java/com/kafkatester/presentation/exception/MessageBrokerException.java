package com.kafkatester.presentation.exception;

public class MessageBrokerException extends RuntimeException {
    public MessageBrokerException(String message) {
        super(MessageBrokerException.class.getName() + System.lineSeparator() + message);
    }
}
