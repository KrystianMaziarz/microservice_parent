package com.pl.bakery.bakery.exception;

import com.pl.bakery.commons.ExceptionClassForHandler;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BakeryExceptionHandler {
    @ExceptionHandler(BakeryNotFoundException.class)
    public ResponseEntity<ExceptionClassForHandler> handleAccessDeniedException(
            BakeryNotFoundException ex) {
        return new ResponseEntity<>(
                new ExceptionClassForHandler(LocalDateTime.now(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
