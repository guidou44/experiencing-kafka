package com.kafkatester.presentation;

import com.kafkatester.domain.messagebroker.MessageListener;
import com.kafkatester.domain.messagebroker.MessageProducer;
import com.kafkatester.domain.messages.TestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("ping")
public class PingController {

    @Autowired private MessageProducer<TestMessage> producer;
    @Autowired private MessageListener<TestMessage> consumer;

    @GetMapping("/heartbeat")
    public String Heartbeat() {
        return "hello world";
    }

    @GetMapping("/produce")
    public String Produce() {
        TestMessage message = new TestMessage();
        message.Message = "YO";
        message.TimeStamp = new Date();
        producer.sendMessage(message);
        return "OK";
    }

    @GetMapping(value = "/consume", produces = "application/json")
    public TestMessage Consume() {
        Optional<TestMessage> messageOpt = consumer.dequeue();
        return messageOpt.orElseGet(TestMessage::new);
    }
}
