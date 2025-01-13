package com.example.demo_kafka_streams.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;

import javax.print.attribute.standard.Destination;
import java.io.IOException;

public class JsonDeserializer<T> implements Deserializer<T> {
    private Class<T> destinationClass;

    JsonDeserializer(Class<T> desitinationClass) {
        this.destinationClass = desitinationClass;
    }


    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


    @Override
    public T deserialize(String s, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            return this.objectMapper.readValue(bytes, destinationClass);
        } catch (IOException e) {
            System.out.println("IOException genric desserializer ----------- " + e.getMessage() + " --- e ---" + e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Exception genric desserializer ---------- " + e.getMessage() + " --- e ---" + e);
            throw new RuntimeException(e);
        }
    }
}
