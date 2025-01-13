package com.example.demo_kafka_streams.serdes;

import com.example.demo_kafka_streams.domains.Greeting;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class GreetingDeserializer implements Deserializer<Greeting> {
    private ObjectMapper objectMapper;

    GreetingDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Greeting deserialize(String s, byte[] bytes) {
        try {
            return this.objectMapper.readValue(bytes, Greeting.class);
        } catch (IOException e) {
            System.out.println("IOException desserializer ----------- " + e.getMessage() + " --- e ---" + e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Exception desserializer ---------- " + e.getMessage() + " --- e ---" + e);
            throw new RuntimeException(e);
        }
    }
}
