package com.pl.bakery.commons;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionClassForHandler {

    private LocalDateTime localDateTime;

    private String message;
}
