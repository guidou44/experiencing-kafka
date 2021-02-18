package com.messagebroker.domain.messages;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TestMessage {
    public String message;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date timeStamp;
}
