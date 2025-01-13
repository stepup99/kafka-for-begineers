package com.example.demo_kafka_streams;

import com.example.demo_kafka_streams.Order.exceptionhandler.StreamProcessorCustomErrorHandler;
import com.example.demo_kafka_streams.Order.topology.OrdersTopology;
import com.example.demo_kafka_streams.topology.ExploreKTableTopology;
import com.example.demo_kafka_streams.topology.GreetingsTopology;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class DemoKafkaStreamsApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoKafkaStreamsApplication.class, args);
        System.out.println("kafka stream application is now running ........ ");

    }


    @Autowired
    private ApplicationContext context;

//
//    @Autowired
//    private GreetingsTopology greetingsTopology;


    @PostConstruct
    public void startKafkaStreams() {
        Map<String, Object> streamConfig = context.getBean("kafkaStreams", Map.class);

//      Topology topology = GreetingsTopology.buildTopology();
//        Topology topology = OrdersTopology.buildTopology();
        Topology topology = ExploreKTableTopology.build();
        
        KafkaStreams streams = new KafkaStreams(topology, new StreamsConfig(streamConfig));
        streams.setUncaughtExceptionHandler(new StreamProcessorCustomErrorHandler());

        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
