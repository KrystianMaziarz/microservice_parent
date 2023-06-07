package com.pl.bakery.pastries;

import com.pl.bakery.pastries.dto.PastryRequestDto;
import com.pl.bakery.pastries.dto.PastryResponseDto;
import com.pl.bakery.pastries.dto.PastryResponseWithIngredientDto;
import java.util.List;

public interface PastryService {

    List<PastryResponseDto> findAllPastries();

    PastryResponseDto addPastry(PastryRequestDto pastryRequestDto);

    PastryResponseDto findById(Long id);

    PastryResponseWithIngredientDto findByIdWithIngredients(Long id);

    PastryResponseDto updatePastry(Long id, PastryRequestDto pastryRequestDto);

    void deleteById(Long id);

    PastryResponseWithIngredientDto addIngredientToPastry(Long pastryId, Long ingredientId);
}
