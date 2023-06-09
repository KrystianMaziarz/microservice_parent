package com.pl.bakery.ingredients;

import com.pl.bakery.ingredients.dto.IngredientRequestDto;
import com.pl.bakery.ingredients.dto.IngredientResponseDto;
import com.pl.bakery.ingredients.dto.IngredientUpdateRequestDto;
import com.pl.bakery.ingredients.dto.IngredientWithQuantityResponseDto;
import java.util.List;

public interface IngredientService {

    List<IngredientResponseDto> findAllIngredients();

    IngredientResponseDto findById(Long id);

    IngredientResponseDto addIngredient(IngredientRequestDto ingredientRequestDto);

    IngredientWithQuantityResponseDto updateIngredient(
            Long id, IngredientUpdateRequestDto ingredientUpdateRequestDto);

    void deleteById(Long id);

    IngredientWithQuantityResponseDto findByIdWithQuantity(Long id);
}
