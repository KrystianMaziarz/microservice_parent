package com.pl.bakery.bakery;

import com.pl.bakery.bakery.dto.BakeryRequestDto;
import com.pl.bakery.bakery.dto.BakeryResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bakeries")
public class BakeryController {

    private final BakeryServiceImpl bakeryService;

    @GetMapping
    public List<BakeryResponseDto> getAll() {

        return bakeryService.findAllBakeries();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BakeryResponseDto addNewBakery(@RequestBody BakeryRequestDto bakeryRequestDto) {

        return bakeryService.addBakery(bakeryRequestDto);
    }

    @PutMapping({"/{id}"})
    public BakeryResponseDto updateBakery(
            @PathVariable Long id, @RequestBody BakeryRequestDto bakeryRequestDto) {

        return bakeryService.updateBakery(id, bakeryRequestDto);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {

        bakeryService.deleteById(id);
    }

    @GetMapping({"/{id}"})
    public BakeryResponseDto getBakeryById(@PathVariable Long id) {

        return bakeryService.findById(id);
    }
}
