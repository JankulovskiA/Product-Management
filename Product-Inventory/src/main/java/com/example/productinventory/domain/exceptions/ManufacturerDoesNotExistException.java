package com.example.productinventory.domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ManufacturerDoesNotExistException extends RuntimeException{
    public ManufacturerDoesNotExistException(Long id) {
        super(String.format("Manufacturer with id: %d was not found", id));
    }
}
