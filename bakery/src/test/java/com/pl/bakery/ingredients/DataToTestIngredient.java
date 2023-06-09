package com.pl.bakery.ingredients;

import com.pl.bakery.ingredients.dto.IngredientRequestDto;
import com.pl.bakery.ingredients.dto.IngredientResponseDto;
import com.pl.bakery.ingredients.dto.IngredientUpdateRequestDto;
import com.pl.bakery.ingredients.dto.IngredientWithQuantityResponseDto;

public class DataToTestIngredient {

    public static final Long ID = 1L;
    public static final int QUANTITY_NUMBER = 10;

    public static IngredientResponseDto getIngredientResponseDto() {

        return new IngredientResponseDto(ID, "Test ingredient name");
    }

    public static IngredientResponseDto getSecondIngredientResponseDto() {

        return new IngredientResponseDto(2L, "Second test ingredient name");
    }

    public static IngredientRequestDto getIngredientAddRequestDto() {

        return new IngredientRequestDto("Test ingredient add name");
    }

    public static IngredientUpdateRequestDto getIngredientUpdateRequestDto() {

        return new IngredientUpdateRequestDto("Test ingredient update name", QUANTITY_NUMBER);
    }

    public static IngredientWithQuantityResponseDto getIngredientUpdateResponseDto() {
        return new IngredientWithQuantityResponseDto(
                ID, "Test ingredient update name", QUANTITY_NUMBER);
    }

    public static IngredientResponseDto getIngredientAddResponseDto() {

        return new IngredientResponseDto(null, "Test ingredient add name");
    }

    public static IngredientWithQuantityResponseDto getIngredientWithQuantityResponseDto() {

        return new IngredientWithQuantityResponseDto(ID, "Test ingredient name", 55);
    }
}
