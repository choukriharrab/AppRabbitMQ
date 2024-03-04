package com.example.UsersMS.MyExceptions;

public class EmptyEntryException extends RuntimeException {
    public EmptyEntryException(String message) {
        super(message);
    }
}
