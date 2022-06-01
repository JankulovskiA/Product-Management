package com.example.ordermanagement.domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderItemDoesNotExistException extends RuntimeException{
    public OrderItemDoesNotExistException(Long id) {
        super(String.format("OrderItem with id: %d was not found", id));
    }
}
