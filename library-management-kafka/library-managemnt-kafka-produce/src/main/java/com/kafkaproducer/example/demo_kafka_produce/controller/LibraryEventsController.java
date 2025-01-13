package com.kafkaproducer.example.demo_kafka_produce.controller;


import com.kafkaproducer.example.demo_kafka_produce.domain.LibraryEvent;
import com.kafkaproducer.example.demo_kafka_produce.producer.LibraryEventsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LibraryEventsController {

    private final LibraryEventsProducer libraryEventsProducer;

    public LibraryEventsController(LibraryEventsProducer libraryEventsProducer) {
        this.libraryEventsProducer = libraryEventsProducer;
    }

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(
        @RequestBody LibraryEvent libraryEvent
    ){
//        log.info("library Event : --------- {}", libraryEvent);
        System.out.println("libraryevent --------- "+libraryEvent);
        libraryEventsProducer.sendLibraryEvent(libraryEvent);
        return  ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

}
