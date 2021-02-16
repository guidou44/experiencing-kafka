package com.kafkatester.domain.messages;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorMessage {

    public String exceptionType;
    public String message;
    public String stackStrace;
    public String ipAddress;

    public ErrorMessage() {
    }

    public ErrorMessage(Exception ex, String ipAddress) {
        String[] namePart = ex.getClass().getName().split("\\.");
        this.exceptionType = namePart[namePart.length - 1];
        this.message = ex.getMessage();
        this.ipAddress = ipAddress;
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        this.stackStrace = sw.toString();
    }
}
