package com.pl.bakery.ingredients;

import com.pl.bakery.ingredients.dto.IngredientRequestDto;
import com.pl.bakery.ingredients.dto.IngredientResponseDto;
import com.pl.bakery.ingredients.dto.IngredientWithQuantityResponseDto;
import org.springframework.stereotype.Service;

@Service
public class IngredientMapper {

    IngredientResponseDto mapIngredientEntityToResponseDto(IngredientEntity ingredientEntity) {

        return new IngredientResponseDto(ingredientEntity.getId(), ingredientEntity.getName());
    }

    IngredientEntity mapIngredientRequestDtoToBakeryEntity(IngredientRequestDto requestDto) {

        return new IngredientEntity(requestDto.getName());
    }

    public IngredientWithQuantityResponseDto mapIngredientEntityToIngredientWithQuantityResponseDto(
            IngredientEntity ingredientEntity) {

        return new IngredientWithQuantityResponseDto(
                ingredientEntity.getId(),
                ingredientEntity.getName(),
                ingredientEntity.getQuantity());
    }
}
