package com.kafkatester.domain.messages;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TestMessage {
    public String Message;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date TimeStamp;
}
