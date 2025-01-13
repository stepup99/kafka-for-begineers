package com.kafkaproducer.example.demo_kafka_produce.producer;

import com.kafkaproducer.example.demo_kafka_produce.domain.Book;
import com.kafkaproducer.example.demo_kafka_produce.domain.LibraryEvent;
import com.kafkaproducer.example.demo_kafka_produce.domain.LibraryEventType;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LibraryEventsProducer {

    @Value("${spring.kafka.topic}")
    public  String  topic;
    private final KafkaTemplate<Integer , String> kafkaTemplate;

    public  LibraryEventsProducer(KafkaTemplate<Integer, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public  void sendLibraryEvent(LibraryEvent libraryEvent){
        var key = 1;
        var book = new Book(10, "BookDate", "Anuj");
        var libraryEvent1 = new LibraryEvent(1, LibraryEventType.NEW, book);
        var completableFuture = kafkaTemplate.send(topic,key, String.valueOf(libraryEvent1));
        completableFuture.whenComplete((sendResult, throwable) ->{
            if(throwable != null){
                handleFailure(key, String.valueOf(libraryEvent1), throwable);
            }else {
                handleSuccess(key, String.valueOf(libraryEvent1), sendResult);
            }
        });
    }

    public CompletableFuture<SendResult<Integer, String>> sendLibraryEvent_approach3(LibraryEvent libraryEvent){
        List<Header> recordHeaders = List.of(new RecordHeader("header-key", "header-value".getBytes()));
        var key = 1;
        var book = new Book(10, "BookDate", "Anuj");
        var libraryEvent1 = new LibraryEvent(1, LibraryEventType.NEW, book);
        var producerDetails = buildProducerRecord(key, String.valueOf(libraryEvent1), recordHeaders);
        var completableFuture = kafkaTemplate.send(producerDetails);


        return completableFuture.whenComplete((sendResult, throwable) ->{
            if(throwable != null){
                handleFailure(key, String.valueOf(libraryEvent1), throwable);
            }else {
                handleSuccess(key, String.valueOf(libraryEvent1), sendResult);
            }
        });
    }


    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, List<Header> recordHeaders){
            return  new ProducerRecord<>(topic ,null, key, value, recordHeaders);
    }



    public  void  handleFailure(Integer key, String value, Throwable throwable){
        System.out.println("handleFailure ->>>>>>>>> "+throwable.getMessage()+" ------- "+ throwable);
    }

    public  void  handleSuccess(Integer key, String value, SendResult<Integer, String> sendResult){
        System.out.println("handle  success ->>>>>>>>> "+value +" ---- "+sendResult.getRecordMetadata().partition());
    }


}
