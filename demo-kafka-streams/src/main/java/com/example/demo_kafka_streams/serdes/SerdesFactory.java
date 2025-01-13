package com.example.demo_kafka_streams.serdes;

import com.example.demo_kafka_streams.domains.Greeting;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class SerdesFactory {
    static public Serde<Greeting> greetingSerdes() {
        return new GreetingSerde();
    }

    static public Serde<Greeting> greetingSerdeUsingGenric() {
        JsonSerializer<Greeting> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<Greeting> jsonDeserializer = new JsonDeserializer<>(Greeting.class);
        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }


}
