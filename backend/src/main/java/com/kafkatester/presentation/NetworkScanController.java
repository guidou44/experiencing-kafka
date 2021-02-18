package com.kafkatester.presentation;

import com.messagebroker.domain.MessageListener;
import com.messagebroker.domain.messages.ErrorMessage;
import com.messagebroker.domain.messages.NetworkScanMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("networkscan")
public class NetworkScanController {

    @Autowired
    private MessageListener<NetworkScanMessage> networkScanConsumer;
    @Autowired
    private MessageListener<ErrorMessage> errorScanConsumer;

    @GetMapping(value = "/networkscan", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NetworkScanMessage> ConsumeNetworkScan() {
        Optional<NetworkScanMessage> messageOpt = networkScanConsumer.dequeue();
        return messageOpt.map(msg -> new ResponseEntity<>(msg, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }

    @GetMapping(value = "/errorscan", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorMessage> ConsumerErrorScan() {
        Optional<ErrorMessage> messageOpt = errorScanConsumer.dequeue();
        return messageOpt.map(err -> new ResponseEntity<>(err, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }
}
