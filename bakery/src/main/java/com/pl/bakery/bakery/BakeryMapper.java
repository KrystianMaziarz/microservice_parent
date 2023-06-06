package com.pl.bakery.bakery;

import com.pl.bakery.bakery.dto.BakeryRequestDto;
import com.pl.bakery.bakery.dto.BakeryResponseDto;
import org.springframework.stereotype.Service;

@Service
public class BakeryMapper {
    // TODO ZMIENIC TO NA MAPSTRUCT

    BakeryResponseDto mapBakeryEntityToBakeryResponseDto(BakeryEntity bakeryEntity) {

        return new BakeryResponseDto(
                bakeryEntity.getId(), bakeryEntity.getName(), bakeryEntity.getLocation());
    }

    BakeryEntity mapBakeryRequestDtoToBakeryEntity(BakeryRequestDto bakeryRequestDto) {

        return new BakeryEntity(
                bakeryRequestDto.getName(),
                bakeryRequestDto.getAddress(),
                bakeryRequestDto.getLocation());
    }
}
