package com.parcialdos.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.parcialdos")
@EnableMongoRepositories(basePackages = "com.parcialdos.Repositoriros")
public class ParcialdosApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParcialdosApplication.class, args);
    }
}