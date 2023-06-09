package com.pl.bakery.pastries;

import static com.pl.bakery.bakery.DataToTestBakery.*;
import static com.pl.bakery.pastries.DataToTestPastry.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pl.bakery.TestContainer;
import com.pl.bakery.pastries.dto.PastryResponseDto;
import com.pl.bakery.pastries.dto.PastryResponseWithIngredientDto;
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
class PastryControllerTestIT extends TestContainer {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void shouldGetAllPastries() {
        // given
        var expected = getPastryResponseDto();
        // when
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/pastries"))
                        .andExpect(status().isOk())
                        .andReturn();
        List<PastryResponseDto> result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(),
                        objectMapper
                                .getTypeFactory()
                                .constructCollectionType(List.class, PastryResponseDto.class));
        // then
        AssertionsForClassTypes.assertThat(result.get(0))
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldGetAllPastriesWithIngredients() {
        // given
        var expected = getPastryResponseWithIngredientDto();
        // when
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/pastries/all"))
                        .andExpect(status().isOk())
                        .andReturn();
        List<PastryResponseWithIngredientDto> result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(),
                        objectMapper
                                .getTypeFactory()
                                .constructCollectionType(
                                        List.class, PastryResponseWithIngredientDto.class));
        // then
        AssertionsForClassTypes.assertThat(result.get(0))
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldGetSinglePastry() {
        // given
        var expected = getPastryResponseDto();
        // when
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/pastries/1"))
                        .andExpect(status().isOk())
                        .andReturn();

        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), PastryResponseDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldGetSinglePastryWithIngredient() {
        // given
        var expected = getPastryResponseWithIngredientDto();
        // when
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/pastries/1/ingredients"))
                        .andExpect(status().isOk())
                        .andReturn();

        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(),
                        PastryResponseWithIngredientDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldDeletePastry() {
        // given
        // when //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/pastries/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @SneakyThrows
    void shouldAddPastry() {
        // given
        var expected = getPastryAddResponseDto();
        var pastryRequestDto = getPastryAddRequestDto();
        // when
        var mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/pastries")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(pastryRequestDto)))
                        .andExpect(status().isCreated())
                        .andReturn();

        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), PastryResponseDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldUpdatePastry() {
        // given
        var expected = getPastryUpdateResponseDto();
        var pastryRequestDto = getPastryUpdateRequestDto();
        // when
        var mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.put("/bakeries/1")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(pastryRequestDto)))
                        .andExpect(status().isOk())
                        .andReturn();
        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), PastryResponseDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldAddIngredientToPastry() {
        // given
        var expected = getPastryResponseWithTwoIngredientsDto();
        // when
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put("/pastries/addIngredient/1/2"))
                        .andExpect(status().isOk())
                        .andReturn();

        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(),
                        PastryResponseWithIngredientDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
