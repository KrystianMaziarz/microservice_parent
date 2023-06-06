package com.pl.bakery.ingredients;

import com.pl.bakery.ingredients.dto.IngredientRequestDto;
import com.pl.bakery.ingredients.dto.IngredientResponseDto;
import com.pl.bakery.ingredients.dto.IngredientUpdateRequestDto;
import java.util.List;

public interface IngredientService {

    List<IngredientResponseDto> findAllIngredients();

    IngredientResponseDto findById(Long id);

    IngredientResponseDto addIngredient(IngredientRequestDto ingredientRequestDto);

    IngredientResponseDto updateIngredient(
            Long id, IngredientUpdateRequestDto ingredientUpdateRequestDto);

    void deleteById(Long id);
}
