package com.example.kafka.publisher;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

@Configuration
public class KafkaProducerConfig {
    @Value("${kafka.server.host}")
    private String serverHost;

    @Value("${kafka.topic.name}")
    private String topicName;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = Map.ofEntries(
                entry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serverHost),
                entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
                entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        );
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public MessageProducer messageProducer(){
        return new MessageProducer(topicName, kafkaTemplate());
    }
}
