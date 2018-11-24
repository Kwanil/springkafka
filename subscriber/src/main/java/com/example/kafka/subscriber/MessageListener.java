package com.example.kafka.subscriber;

import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "a", containerFactory = "aKafkaListenerContainerFactory")
    public void listenGroupA(String message) {
        System.out.println("Received Message in group 'a': " + message);
    }

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "b", containerFactory = "bKafkaListenerContainerFactory")
    public void listenGroupB(String message) {
        System.out.println("Received Message in group 'b': " + message);
    }
}
