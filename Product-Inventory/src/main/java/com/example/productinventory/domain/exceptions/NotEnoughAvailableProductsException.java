package com.example.productinventory.domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotEnoughAvailableProductsException extends RuntimeException {
    public NotEnoughAvailableProductsException(Long id) {
        super(String.format("Product with id: %d isn't available in that number", id));
    }

}
