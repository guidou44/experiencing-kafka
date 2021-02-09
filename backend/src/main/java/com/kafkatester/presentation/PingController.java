package com.kafkatester.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ping")
public class PingController {

    @GetMapping("/heartbeat")
    public String Heartbeat() {
        return "hello world";
    }
}
