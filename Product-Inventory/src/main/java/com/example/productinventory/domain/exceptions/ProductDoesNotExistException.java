package com.example.productinventory.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductDoesNotExistException extends RuntimeException{
    public ProductDoesNotExistException(Long id) {
        super(String.format("Product with id: %d was not found", id));
    }
}
