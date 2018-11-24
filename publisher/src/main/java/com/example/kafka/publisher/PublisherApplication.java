package com.example.kafka.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublisherApplication {

    public static void main(String[] args) {
        try(var context = SpringApplication.run(PublisherApplication.class, args)) {
            var messageProducer = context.getBean(MessageProducer.class);
            messageProducer.sendMessage("Hello world");
        }
    }
}
