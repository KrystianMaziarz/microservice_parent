package com.pl.bakery.bakery;

import com.pl.bakery.bakery.dto.BakeryRequestDto;
import com.pl.bakery.bakery.dto.BakeryResponseDto;

public class DataToTestBakery {
    public static final Long ID = 1L;

    public static BakeryResponseDto getBakeryResponseDto() {

        return new BakeryResponseDto(ID, "Test bakery name", "Test bakery location");
    }

    public static BakeryRequestDto getBakeryUpdateRequestDto() {

        return new BakeryRequestDto(
                "Test updated bakery name",
                ("Test updated bakery location"),
                ("Test updated bakery address"));
    }

    public static BakeryResponseDto getBakeryUpdatedResponseDto() {

        return new BakeryResponseDto(
                ID, "Test updated bakery name", "Test updated bakery location");
    }

    public static BakeryRequestDto getBakeryAddRequestDto() {

        return new BakeryRequestDto(
                "Test add bakery name", "Test add bakery location", "Test add bakery address");
    }

    public static BakeryResponseDto getBakeryAddResponseDto() {

        return new BakeryResponseDto(null, "Test add bakery name", "Test add bakery location");
    }
}
