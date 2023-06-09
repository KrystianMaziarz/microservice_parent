package com.pl.bakery.pastries;

import static com.pl.bakery.ingredients.DataToTestIngredient.getIngredientResponseDto;
import static com.pl.bakery.ingredients.DataToTestIngredient.getSecondIngredientResponseDto;

import com.pl.bakery.pastries.dto.PastryRequestDto;
import com.pl.bakery.pastries.dto.PastryResponseDto;
import com.pl.bakery.pastries.dto.PastryResponseWithIngredientDto;
import java.util.Set;

public class DataToTestPastry {

    public static final Long ID = 1L;

    public static PastryResponseDto getPastryResponseDto() {

        return new PastryResponseDto(ID, "Test pastry name");
    }

    public static PastryRequestDto getPastryAddRequestDto() {

        return new PastryRequestDto("Test add pastry name");
    }

    public static PastryResponseDto getPastryAddResponseDto() {

        return new PastryResponseDto(null, "Test add pastry name");
    }

    public static PastryResponseWithIngredientDto getPastryResponseWithIngredientDto() {

        return new PastryResponseWithIngredientDto(
                "Test pastry name", Set.of(getIngredientResponseDto()));
    }

    public static PastryResponseWithIngredientDto getPastryResponseWithTwoIngredientsDto() {

        return new PastryResponseWithIngredientDto(
                "Test pastry name",
                Set.of(getIngredientResponseDto(), getSecondIngredientResponseDto()));
    }

    public static PastryRequestDto getPastryUpdateRequestDto() {

        return new PastryRequestDto("Test update pastry name");
    }

    public static PastryResponseDto getPastryUpdateResponseDto() {
        return new PastryResponseDto(ID, "Test update pastry name");
    }
}
