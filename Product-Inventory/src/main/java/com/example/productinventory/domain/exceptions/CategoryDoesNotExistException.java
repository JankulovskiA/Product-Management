package com.example.productinventory.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CategoryDoesNotExistException extends RuntimeException{
    public CategoryDoesNotExistException(Long id) {
        super(String.format("Category with id: %d was not found", id));
    }
}
