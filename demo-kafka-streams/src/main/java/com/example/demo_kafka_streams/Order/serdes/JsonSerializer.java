package com.example.demo_kafka_streams.Order.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Serializer;

public class JsonSerializer<T> implements Serializer<T> {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


    @Override
    public byte[] serialize(String s, T t) {
        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException genric serializer " + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Exception genric serializer " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
