package com.kafkaproducer.example.demo_kafka_produce.domain;

public class LibraryEvent {
    private Integer libraryEventId;
    private LibraryEventType libraryEventType;
    private Book book;

    // All-Arguments Constructor
    public LibraryEvent(Integer libraryEventId, LibraryEventType libraryEventType, Book book) {
        this.libraryEventId = libraryEventId;
        this.libraryEventType = libraryEventType;
        this.book = book;
    }

    // Getters
    public Integer getLibraryEventId() {
        return libraryEventId;
    }

    public LibraryEventType getLibraryEventType() {
        return libraryEventType;
    }

    public Book getBook() {
        return book;
    }

    // Setters
    public void setLibraryEventId(Integer libraryEventId) {
        this.libraryEventId = libraryEventId;
    }

    public void setLibraryEventType(LibraryEventType libraryEventType) {
        this.libraryEventType = libraryEventType;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    // toString Method
    @Override
    public String toString() {
        return "LibraryEvent{" +
                "libraryEventId=" + libraryEventId +
                ", libraryEventType=" + libraryEventType +
                ", book=" + book +
                '}';
    }
}
