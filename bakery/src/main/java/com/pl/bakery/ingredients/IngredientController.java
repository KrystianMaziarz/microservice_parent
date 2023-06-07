package com.pl.bakery.ingredients;

import com.pl.bakery.ingredients.dto.IngredientRequestDto;
import com.pl.bakery.ingredients.dto.IngredientResponseDto;
import com.pl.bakery.ingredients.dto.IngredientUpdateRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientServiceImpl ingredientService;

    @GetMapping
    public List<IngredientResponseDto> getAll() {

        return ingredientService.findAllIngredients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientResponseDto addNewIngredient(
            @RequestBody IngredientRequestDto ingredientRequestDto) {

        return ingredientService.addIngredient(ingredientRequestDto);
    }

    @PutMapping("/{id}")
    public IngredientResponseDto updateIngredient(
            @PathVariable Long id,
            @RequestBody IngredientUpdateRequestDto ingredientUpdateRequestDto) {

        return ingredientService.updateIngredient(id, ingredientUpdateRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        ingredientService.deleteById(id);
    }
}
