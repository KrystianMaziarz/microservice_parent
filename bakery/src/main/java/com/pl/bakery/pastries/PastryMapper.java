package com.pl.bakery.pastries;

import com.pl.bakery.ingredients.IngredientEntity;
import com.pl.bakery.ingredients.dto.IngredientResponseDto;
import com.pl.bakery.pastries.dto.PastryRequestDto;
import com.pl.bakery.pastries.dto.PastryResponseDto;
import com.pl.bakery.pastries.dto.PastryResponseWithIngredientDto;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PastryMapper {

    PastryResponseDto mapPastryEntityToPastryResponseDto(PastryEntity pastryEntity) {

        return new PastryResponseDto(pastryEntity.getId(), pastryEntity.getName());
    }

    PastryEntity mapPastryRequestDtoToPastryEntity(PastryRequestDto pastryRequestDto) {

        return new PastryEntity(pastryRequestDto.getName());
    }

    public PastryResponseWithIngredientDto mapPastryEntityToPastryResponseWithIngredientDto(
            PastryEntity pastryEntity) {

        return new PastryResponseWithIngredientDto(
                pastryEntity.getName(), mapIngredientsToDto(pastryEntity.getIngredientSet()));
    }

    private Set<IngredientResponseDto> mapIngredientsToDto(
            Set<IngredientEntity> ingredientEntities) {
        return ingredientEntities.stream()
                .map(
                        ingredientEntity -> {
                            IngredientResponseDto ingredientResponseDto =
                                    new IngredientResponseDto();
                            ingredientResponseDto.setName(ingredientEntity.getName());
                            ingredientResponseDto.setId(ingredientEntity.getId());
                            return ingredientResponseDto;
                        })
                .collect(Collectors.toSet());
    }
}
