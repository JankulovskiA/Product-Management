package com.example.ordermanagement.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductDoesNotExitsException extends RuntimeException{
    public ProductDoesNotExitsException(Long id) {
        super(String.format("product with id: %d was not found", id));
    }
}
