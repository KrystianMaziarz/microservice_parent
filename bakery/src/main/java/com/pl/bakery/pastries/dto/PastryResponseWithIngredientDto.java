package com.pl.bakery.pastries.dto;

import com.pl.bakery.ingredients.dto.IngredientResponseDto;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PastryResponseWithIngredientDto {

    private String name;
    private Set<IngredientResponseDto> ingredientResponseDto;
}
