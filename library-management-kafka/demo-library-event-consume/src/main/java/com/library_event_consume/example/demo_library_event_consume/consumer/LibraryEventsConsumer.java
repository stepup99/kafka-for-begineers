package com.library_event_consume.example.demo_library_event_consume.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LibraryEventsConsumer {
    @KafkaListener(topics = "library-events", groupId = "group1")
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord){
        System.out.println("consumer record -----------" + consumerRecord);
    }
}
