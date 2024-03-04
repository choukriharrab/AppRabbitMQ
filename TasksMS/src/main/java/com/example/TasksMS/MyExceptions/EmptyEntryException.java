package com.example.TasksMS.MyExceptions;

public class EmptyEntryException extends RuntimeException {
    public EmptyEntryException(String message) {
        super(message);
    }
}
