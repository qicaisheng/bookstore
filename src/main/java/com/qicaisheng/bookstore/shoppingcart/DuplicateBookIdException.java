package com.qicaisheng.bookstore.shoppingcart;

public class DuplicateBookIdException extends RuntimeException {
    public DuplicateBookIdException(String message) {
        super(message);
    }
}