package com.example.ordermanagement.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderDoesNotExistException extends RuntimeException{
    public OrderDoesNotExistException(Long id) {
        super(String.format("Order with id: %d was not found", id));
    }
}
