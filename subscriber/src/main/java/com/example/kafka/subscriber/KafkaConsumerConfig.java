package com.example.kafka.subscriber;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

import static java.util.Map.entry;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${kafka.server.host}")
    private String serverHost;

    ConsumerFactory<String, String> consumerFactory(String groupId) {
        Map<String, Object> configProps = Map.ofEntries(
                entry(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serverHost),
                entry(ConsumerConfig.GROUP_ID_CONFIG, groupId),
                entry(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class),
                entry(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
        );
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> aKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory("a"));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> bKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory("b"));
        return factory;
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }
}
