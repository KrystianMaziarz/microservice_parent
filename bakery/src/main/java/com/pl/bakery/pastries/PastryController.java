package com.pl.bakery.pastries;

import com.pl.bakery.pastries.dto.PastryRequestDto;
import com.pl.bakery.pastries.dto.PastryResponseDto;
import com.pl.bakery.pastries.dto.PastryResponseWithIngredientDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pastries")
public class PastryController {

    private final PastryServiceImpl pastryService;

    @GetMapping
    public List<PastryResponseDto> getAll() {
        return pastryService.findAllPastries();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PastryResponseDto addNewPastry(@RequestBody PastryRequestDto pastryRequestDto) {

        return pastryService.addPastry(pastryRequestDto);
    }

    @PutMapping("/{id}")
    public PastryResponseDto updatePastry(
            @PathVariable Long id, @RequestBody PastryRequestDto pastryRequestDto) {

        return pastryService.updatePastry(id, pastryRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePastry(@PathVariable Long id) {

        pastryService.deleteById(id);
    }

    @GetMapping("/{id}")
    public PastryResponseDto getPastry(@PathVariable Long id) {

        return pastryService.findById(id);
    }

    @PutMapping("addIngredient/{pastryId}/{ingredientId}")
    public PastryResponseWithIngredientDto addIngredient(
            @PathVariable Long pastryId, @PathVariable Long ingredientId) {

        return pastryService.addIngredientToPastry(pastryId, ingredientId);
    }

    @GetMapping("/{id}/ingredients")
    public PastryResponseWithIngredientDto getPastryWithIngredients(@PathVariable Long id) {

        return pastryService.findByIdWithIngredients(id);
    }
}
