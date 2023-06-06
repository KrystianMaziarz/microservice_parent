package com.pl.bakery.bakery.exception;

public class BakeryNotFoundException extends RuntimeException {
    public BakeryNotFoundException(Long id) {
        super(String.format("Bakery with id : %s not found", id));
    }
}
