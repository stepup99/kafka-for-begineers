package com.example.demo_kafka_streams.Order.exceptionhandler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.errors.DeserializationExceptionHandler;
import org.apache.kafka.streams.processor.ProcessorContext;

import java.sql.SQLOutput;
import java.util.Map;

public class StreamsDeserializationExceptionHandler implements DeserializationExceptionHandler {
    int errorCounter = 0;


    // throw new IllegalStateException (value.message());
    @Override
    public DeserializationHandlerResponse handle(ProcessorContext processorContext, ConsumerRecord<byte[], byte[]> consumerRecord, Exception e) {
        System.out.println("Exception is : " + e.getMessage() + " and the kafka record is " + consumerRecord + "and full exeception " + e);
        System.out.println("errorCountor -------- " + errorCounter);
        if (errorCounter < 2) {
            errorCounter++;
            return DeserializationHandlerResponse.CONTINUE;
        }
        return DeserializationHandlerResponse.FAIL;
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
