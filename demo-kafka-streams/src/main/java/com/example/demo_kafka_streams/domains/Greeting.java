package com.example.demo_kafka_streams.domains;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Greeting {
    private String message;
    private LocalDateTime timeStamp;

    @JsonCreator
    public Greeting(@JsonProperty("message") String message,
                    @JsonProperty("timeStamp") LocalDateTime timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter for timeStamp
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    // Setter for timeStamp
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    // toString method
    @Override
    public String toString() {
        return "Greeting{" +
                "message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
