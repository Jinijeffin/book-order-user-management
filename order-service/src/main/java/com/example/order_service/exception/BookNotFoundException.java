package com.example.order_service.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id){

        super("Book not found with id: " + id);
    }


}