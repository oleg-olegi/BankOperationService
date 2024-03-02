package com.example.bankoperationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankOperationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankOperationServiceApplication.class, args);
    }

}
