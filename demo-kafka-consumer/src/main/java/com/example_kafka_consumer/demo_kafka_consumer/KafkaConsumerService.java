package com.example_kafka_consumer.demo_kafka_consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {

    // Consumer 1 of group1 (will consume from one or more partitions)
    @KafkaListener(topics = "test-topic", groupId = "group1", id = "consumer1")
    public void consume1(ConsumerRecord<String, String> record) {
        System.out.println("Consumer 1 received message: " + record.value());
    }

    // Consumer 2 of group1 (will consume from one or more partitions)
    @KafkaListener(topics = "test-topic", groupId = "group1", id = "consumer2")
    public void consume2(ConsumerRecord<String, String> record) {
        System.out.println("Consumer 2 received message: " + record.value());
    }

//     Consumer 3 of group1 (will consume from one or more partitions)
    @KafkaListener(topics = "test-topic", groupId = "group1", id = "consumer3")
    public void consume3(ConsumerRecord<String, String> record) {
        System.out.println("Consumer 3 received message: " + record.value());
    }

    // Consumer 4 of group1 (will consume from one or more partitions)
//    @KafkaListener(topics = "test-topic", groupId = "group1", id = "consumer4")
//    public void consume4(ConsumerRecord<String, String> record) {
//        System.out.println("Consumer 4 received message: " + record.value());
//    }
}
