package com.kafkatester.domain.messages;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorMessage {

    public String ExceptionType;
    public String Message;
    public String StackStrace;

    public ErrorMessage(Exception ex) {
        this.ExceptionType = ex.getClass().getCanonicalName();
        this.Message = ex.getMessage();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        this.StackStrace = sw.toString();
    }
}
