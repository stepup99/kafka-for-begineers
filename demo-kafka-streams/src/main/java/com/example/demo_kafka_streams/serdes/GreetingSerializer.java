package com.example.demo_kafka_streams.serdes;

import com.example.demo_kafka_streams.domains.Greeting;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.kafka.common.serialization.Serializer;

public class GreetingSerializer implements Serializer<Greeting> {

    private ObjectMapper objectMapper;

    public GreetingSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public byte[] serialize(String s, Greeting greeting) {
        try {
            return objectMapper.writeValueAsBytes(greeting);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException serializer " + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Exception serializer " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
