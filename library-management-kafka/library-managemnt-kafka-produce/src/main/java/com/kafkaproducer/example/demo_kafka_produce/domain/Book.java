package com.kafkaproducer.example.demo_kafka_produce.domain;

public class Book {
    private Integer bookId;
    private String bookName;
    private String bookAuthor;

    // Manually add constructor
    public Book(Integer bookId, String bookName, String bookAuthor) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    // Manually add no-arg constructor (if needed)
    public Book() {}

    // Manually add getters and setters (or use Lombok)
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}
