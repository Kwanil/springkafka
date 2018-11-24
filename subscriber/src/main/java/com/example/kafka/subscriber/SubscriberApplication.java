package com.example.kafka.subscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SubscriberApplication {

    public static void main(String[] args) throws Exception {
        try (var applicationContext = SpringApplication.run(SubscriberApplication.class, args)) {
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
