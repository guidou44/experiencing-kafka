package com.kafkatester.domain.messages;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorMessage {

    public String exceptionType;
    public String message;
    public String stackStrace;

    public ErrorMessage() {
    }

    public ErrorMessage(Exception ex) {
        this.exceptionType = ex.getClass().getCanonicalName();
        this.message = ex.getMessage();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        this.stackStrace = sw.toString();
    }
}
