package com.kafkatester;

import com.kafkatester.domain.network.NetworkScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(MainApplication.class, args);
    Thread networkScannerWorker = new Thread(context.getBean(NetworkScanner.class));
    networkScannerWorker.start();
  }
}
