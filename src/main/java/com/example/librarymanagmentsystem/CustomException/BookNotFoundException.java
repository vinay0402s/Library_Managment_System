package com.example.librarymanagmentsystem.CustomException;

//custom Exception
public class BookNotFoundException extends Exception {

    public BookNotFoundException(String message) {
        super(message);
    }
}
