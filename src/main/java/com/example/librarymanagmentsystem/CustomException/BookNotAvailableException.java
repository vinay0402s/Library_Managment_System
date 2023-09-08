package com.example.librarymanagmentsystem.CustomException;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}
