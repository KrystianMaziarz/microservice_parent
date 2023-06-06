package com.pl.bakery.bakery;

import com.pl.bakery.bakery.dto.BakeryRequestDto;
import com.pl.bakery.bakery.dto.BakeryResponseDto;
import java.util.List;

public interface BakeryService {
    List<BakeryResponseDto> findAllBakeries();

    BakeryResponseDto addBakery(BakeryRequestDto bakeryRequestDto);

    BakeryResponseDto findById(Long id);

    BakeryResponseDto updateBakery(Long id, BakeryRequestDto bakeryRequestDto);

    void deleteById(Long id);
}
