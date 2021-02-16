package com.kafkatester.domain.network.exceptions;

public class VolunteerChaosException extends RuntimeException {
    private String ipAddress;
    public VolunteerChaosException(String message, String ipAddress) {
        super(VolunteerChaosException.class.getName() + System.lineSeparator() + message);
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }
}
