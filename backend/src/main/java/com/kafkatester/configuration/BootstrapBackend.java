package com.kafkatester.configuration;

import com.messagebroker.BootstrapBroker;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {BootstrapBroker.class})
public class BootstrapBackend {
}
