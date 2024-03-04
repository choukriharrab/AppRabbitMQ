package com.example.UsersMS.MyExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Arrays.asList(ex.getMessage()));
        // Return ResponseEntity
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(NotFoundException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage()));
        // Return ResponseEntity
        return new ResponseEntity<>(errorBody, errorBody.getStatus());    }

    @ExceptionHandler(DataNotValidException.class)
    public ResponseEntity<?> handleDataNotValidException(DataNotValidException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Arrays.asList(ex.getMessage()));
        // Return ResponseEntity
        return new ResponseEntity<>(errorBody, errorBody.getStatus());    }

    @ExceptionHandler(EmptyEntryException.class)
    public ResponseEntity<?> emptyEntryExceptionHandler(EmptyEntryException ex){
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Arrays.asList(ex.getMessage()));
        // Return ResponseEntity
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }


}
