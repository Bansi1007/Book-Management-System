package com.bansi.bookManagement.exception;

public class BookAlreadyExist extends RuntimeException {
    public BookAlreadyExist(String message) {
        super(message);
    }
}
