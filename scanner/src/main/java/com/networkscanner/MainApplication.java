package com.networkscanner;

import com.networkscanner.scanner.NetworkScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MainApplication.class);

    @Autowired
    private NetworkScanner scanner;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(MainApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("EXECUTING : NetworkScanner");
        scanner.run();
    }
}
