package com.networkscanner.configuration;

import com.messagebroker.BootstrapBroker;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {BootstrapBroker.class})
public class BootstrapScanner {
}
