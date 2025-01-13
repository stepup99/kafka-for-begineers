package com.example.demo_kafka_streams.controller;


import com.example.demo_kafka_streams.Order.domains.Order;
import com.example.demo_kafka_streams.domains.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.Map;


@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Greeting> kafkaTemplateGreeting;

    @Autowired
    private KafkaTemplate<String, Order> orderKafkaTemplate;

    private static final String TOPIC = "greetings";
    private static final String ORDER = "orders";
    private static final String WORDS = "words";

    @PostMapping("/send-message")
    public String sendMessage(@RequestBody String message) {
        kafkaTemplate.send(TOPIC, message);
        return "Message send to kafka" + message;
    }

    @PostMapping("/send-array")
    public ResponseEntity<String> sendArrayToKafka(@RequestBody int[] numbers) {
        System.out.println("before -------- " + numbers);
        String message = converArrayToString(numbers);
        System.out.println("after -------- " + message);
        kafkaTemplate.send(TOPIC, message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @PostMapping("/send-order")
    public ResponseEntity<Order> sendOrderToKafka(@RequestBody Order order) {
        orderKafkaTemplate.send(ORDER, order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @PostMapping("/send-json")
    public ResponseEntity<Greeting> sendJsonToKafka(@RequestBody Greeting greeting) {
        kafkaTemplateGreeting.send(TOPIC, greeting);
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }


    @PostMapping("/send-ktable")
    public ResponseEntity<Map<String, String>> sendKtable(@RequestBody Map<String, String> m) {
        String k = m.get("key");
        String v = m.get("value");
        kafkaTemplate.send(WORDS, k, v);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    private String converArrayToString(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number).append(",");
        }

        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }


        return sb.toString();


    }

}
