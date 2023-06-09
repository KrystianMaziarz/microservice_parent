package com.pl.bakery.ingredients;

import com.pl.bakery.ingredients.dto.IngredientRequestDto;
import com.pl.bakery.ingredients.dto.IngredientResponseDto;
import com.pl.bakery.ingredients.dto.IngredientUpdateRequestDto;
import com.pl.bakery.ingredients.dto.IngredientWithQuantityResponseDto;
import com.pl.bakery.ingredients.exception.IngredientNotFoundException;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    public List<IngredientResponseDto> findAllIngredients() {

        return ingredientRepository.findAll().stream()
                .map(ingredientMapper::mapIngredientEntityToResponseDto)
                .toList();
    }

    @Override
    public IngredientResponseDto findById(Long id) {
        IngredientEntity ingredientEntity =
                ingredientRepository
                        .findById(id)
                        .orElseThrow(() -> new IngredientNotFoundException(id));
        return ingredientMapper.mapIngredientEntityToResponseDto(ingredientEntity);
    }

    @Override
    @Transactional
    public IngredientResponseDto addIngredient(IngredientRequestDto ingredientRequestDto) {
        IngredientEntity ingredientEntity =
                ingredientMapper.mapIngredientRequestDtoToBakeryEntity(ingredientRequestDto);

        log.info("Ingredient {} is saved", ingredientEntity.getId());
        return ingredientMapper.mapIngredientEntityToResponseDto(ingredientEntity);
    }

    @Override
    @Transactional
    public IngredientWithQuantityResponseDto updateIngredient(
            Long id, IngredientUpdateRequestDto ingredientUpdateRequestDto) {

        IngredientEntity ingredientEntity =
                ingredientRepository
                        .findById(id)
                        .orElseThrow(() -> new IngredientNotFoundException(id));

        Optional.ofNullable(ingredientUpdateRequestDto.getName())
                .filter(StringUtils::isNotBlank)
                .ifPresent(ingredientEntity::setName);
        Optional.ofNullable(ingredientUpdateRequestDto.getQuantity())
                .ifPresent(ingredientEntity::setQuantity);

        log.info("Ingredient {} is updated", ingredientEntity.getId());
        return ingredientMapper.mapIngredientEntityToIngredientWithQuantityResponseDto(
                ingredientEntity);
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    public IngredientWithQuantityResponseDto findByIdWithQuantity(Long id) {
        IngredientEntity ingredientEntity =
                ingredientRepository
                        .findById(id)
                        .orElseThrow(() -> new IngredientNotFoundException(id));
        return ingredientMapper.mapIngredientEntityToIngredientWithQuantityResponseDto(
                ingredientEntity);
    }
}
