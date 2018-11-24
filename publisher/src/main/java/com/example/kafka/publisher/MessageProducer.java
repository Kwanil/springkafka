package com.example.kafka.publisher;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.util.concurrent.ListenableFuture;

public class MessageProducer {
    private String topicName;
    private KafkaTemplate<String, String> kafkaTemplate;

    public MessageProducer(@NonNull String topicName,@NonNull KafkaTemplate<String, String> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, msg);
        future.addCallback(System.out::println, e-> System.out.println("ERROR : " +  e));
    }
}
