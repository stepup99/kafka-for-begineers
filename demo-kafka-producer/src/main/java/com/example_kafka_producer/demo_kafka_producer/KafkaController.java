package com.example_kafka_producer.demo_kafka_producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public  String publishMessage(@RequestParam("message") String message){
        kafkaProducerService.sendMessage(message);
        return  "message sent to kafka";
    }

}
