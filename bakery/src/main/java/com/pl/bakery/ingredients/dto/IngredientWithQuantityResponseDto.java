package com.pl.bakery.ingredients.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientWithQuantityResponseDto {

    private Long id;

    private String name;

    private int quantity;
}
