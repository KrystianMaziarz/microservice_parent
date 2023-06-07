package com.pl.bakery.pastries.exception;

public class PastryNotFoundException extends RuntimeException {

    public PastryNotFoundException(Long id) {
        super(String.format("Ingredient with id : %s not found", id));
    }
}
