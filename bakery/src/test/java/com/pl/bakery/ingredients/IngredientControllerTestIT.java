package com.pl.bakery.ingredients;

import static com.pl.bakery.ingredients.DataToTestIngredient.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pl.bakery.TestContainer;
import com.pl.bakery.ingredients.dto.IngredientResponseDto;
import java.util.List;
import lombok.SneakyThrows;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class IngredientControllerTestIT extends TestContainer {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void shouldGetAllIngredients() {
        // given
        var expected = getIngredientResponseDto();
        // when
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/ingredients"))
                        .andExpect(status().isOk())
                        .andReturn();
        List<IngredientResponseDto> result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(),
                        objectMapper
                                .getTypeFactory()
                                .constructCollectionType(List.class, IngredientResponseDto.class));
        // then
        AssertionsForClassTypes.assertThat(result.get(0))
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldGetSingleIngredient() {
        // given
        var expected = getIngredientResponseDto();
        // when
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/1"))
                        .andExpect(status().isOk())
                        .andReturn();

        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), IngredientResponseDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldGetSingleIngredientWithQuantity() {
        // given
        var expected = getIngredientWithQuantityResponseDto();
        // when
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/all/1"))
                        .andExpect(status().isOk())
                        .andReturn();

        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), IngredientResponseDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldDeleteIngredient() {
        // given
        // when //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/ingredients/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @SneakyThrows
    void shouldAddIngredient() {
        // given
        var expected = getIngredientAddResponseDto();
        var ingredientAddRequestDto = getIngredientAddRequestDto();
        // when
        var mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/ingredients")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(
                                                objectMapper.writeValueAsString(
                                                        ingredientAddRequestDto)))
                        .andExpect(status().isCreated())
                        .andReturn();

        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), IngredientResponseDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldUpdateIngredient() {
        // given
        var expected = getIngredientUpdateResponseDto();
        var ingredientUpdateRequestDto = getIngredientUpdateRequestDto();
        // when
        var mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.put("/ingredients/1")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(
                                                objectMapper.writeValueAsString(
                                                        ingredientUpdateRequestDto)))
                        .andExpect(status().isOk())
                        .andReturn();
        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), IngredientResponseDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
