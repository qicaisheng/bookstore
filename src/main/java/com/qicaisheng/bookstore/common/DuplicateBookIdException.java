package com.qicaisheng.bookstore.common;

public class DuplicateBookIdException extends RuntimeException {
    public DuplicateBookIdException(String message) {
        super(message);
    }
}