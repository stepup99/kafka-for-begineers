package com.example.demo_kafka_streams.Order.serdes;

import com.example.demo_kafka_streams.Order.domains.Order;
import com.example.demo_kafka_streams.Order.domains.Revenue;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class SerdesFactory {
    static public Serde<Order> orderSerdeUsingGenric() {
        JsonSerializer<Order> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<Order> jsonDeserializer = new JsonDeserializer<>(Order.class);
        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }

    static public Serde<Revenue> revenueSerde() {
        JsonSerializer<Revenue> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<Revenue> jsonDeserializer = new JsonDeserializer<>(Revenue.class);
        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }

}
