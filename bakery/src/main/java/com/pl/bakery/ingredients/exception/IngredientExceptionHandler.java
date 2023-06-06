package com.pl.bakery.ingredients.exception;

import com.pl.bakery.commons.ExceptionClassForHandler;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IngredientExceptionHandler {
    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<ExceptionClassForHandler> handleAccessDeniedException(
            IngredientNotFoundException ex) {
        return new ResponseEntity<>(
                new ExceptionClassForHandler(LocalDateTime.now(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
