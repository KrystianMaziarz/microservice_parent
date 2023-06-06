package com.pl.bakery.ingredients.exception;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Long id) {
        super(String.format("Ingredient with id : %s not found", id));
    }
}
