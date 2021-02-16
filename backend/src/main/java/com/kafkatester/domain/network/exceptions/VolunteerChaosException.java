package com.kafkatester.domain.network.exceptions;

public class VolunteerChaosException extends RuntimeException {
    public VolunteerChaosException(String message) {
        super(VolunteerChaosException.class.getName() + System.lineSeparator() + message);
    }
}
