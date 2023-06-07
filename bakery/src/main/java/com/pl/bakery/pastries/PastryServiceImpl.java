package com.pl.bakery.pastries;

import com.pl.bakery.ingredients.IngredientEntity;
import com.pl.bakery.ingredients.IngredientRepository;
import com.pl.bakery.ingredients.exception.IngredientNotFoundException;
import com.pl.bakery.pastries.dto.PastryRequestDto;
import com.pl.bakery.pastries.dto.PastryResponseDto;
import com.pl.bakery.pastries.dto.PastryResponseWithIngredientDto;
import com.pl.bakery.pastries.exception.PastryNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PastryServiceImpl implements PastryService {

    private final PastryRepository pastryRepository;

    private final IngredientRepository ingredientRepository;

    private final PastryMapper pastryMapper;

    @Override
    public List<PastryResponseDto> findAllPastries() {
        return pastryRepository.findAll().stream()
                .map(pastryMapper::mapPastryEntityToPastryResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public PastryResponseDto addPastry(PastryRequestDto pastryRequestDto) {
        PastryEntity pastryEntity =
                pastryMapper.mapPastryRequestDtoToPastryEntity(pastryRequestDto);

        log.info("Pastry {} created", pastryEntity.getId());
        return pastryMapper.mapPastryEntityToPastryResponseDto(pastryEntity);
    }

    @Override
    public PastryResponseDto findById(Long id) {
        PastryEntity pastryEntity =
                pastryRepository.findById(id).orElseThrow(() -> new PastryNotFoundException(id));
        return pastryMapper.mapPastryEntityToPastryResponseDto(pastryEntity);
    }

    @Override
    public PastryResponseWithIngredientDto findByIdWithIngredients(Long id) {
        PastryEntity pastryEntity =
                pastryRepository.findById(id).orElseThrow(() -> new PastryNotFoundException(id));
        return pastryMapper.mapPastryEntityToPastryResponseWithIngredientDto(pastryEntity);
    }

    @Override
    @Transactional
    public PastryResponseDto updatePastry(Long id, PastryRequestDto pastryRequestDto) {
        PastryEntity pastryEntity =
                pastryRepository.findById(id).orElseThrow(() -> new PastryNotFoundException(id));

        Optional.ofNullable(pastryEntity.getName())
                .filter(StringUtils::isNoneBlank)
                .ifPresent(pastryEntity::setName);

        log.info("Pastry {} updated", pastryEntity.getId());
        return pastryMapper.mapPastryEntityToPastryResponseDto(pastryEntity);
    }

    @Override
    public void deleteById(Long id) {

        pastryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public PastryResponseWithIngredientDto addIngredientToPastry(Long pastryId, Long ingredientId) {
        PastryEntity pastryEntity =
                pastryRepository
                        .findById(pastryId)
                        .orElseThrow(() -> new PastryNotFoundException(pastryId));
        IngredientEntity ingredientEntity =
                ingredientRepository
                        .findById(ingredientId)
                        .orElseThrow(() -> new IngredientNotFoundException(ingredientId));

        pastryEntity.addIngredient(ingredientEntity);
        ingredientEntity.addPastry(pastryEntity);

        log.info("Ingredient {} added to {}", ingredientEntity.getId(), pastryEntity.getId());

        return pastryMapper.mapPastryEntityToPastryResponseWithIngredientDto(pastryEntity);
    }
}
