package com.pl.bakery.bakery;

import static com.pl.bakery.bakery.DataToTestBakery.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pl.bakery.TestContainer;
import com.pl.bakery.bakery.dto.BakeryResponseDto;
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
class BakeryControllerTestIT extends TestContainer {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void shouldGetAllBakeries() {
        // given
        var expected = getBakeryResponseDto();
        // when
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/bakeries"))
                        .andExpect(status().isOk())
                        .andReturn();
        List<BakeryResponseDto> result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(),
                        objectMapper
                                .getTypeFactory()
                                .constructCollectionType(List.class, BakeryResponseDto.class));
        // then
        AssertionsForClassTypes.assertThat(result.get(0))
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldGetSingleBakery() {
        // given
        var expected = getBakeryResponseDto();
        // when
        var mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/bakeries/1"))
                        .andExpect(status().isOk())
                        .andReturn();

        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), BakeryResponseDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldDeleteBakery() {
        // given
        // when //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/bakeries/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @SneakyThrows
    void shouldAddBakery() {
        // given
        var expected = getBakeryAddResponseDto();
        var bakeryRequestDto = getBakeryAddRequestDto();
        // when
        var mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/bakeries")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(bakeryRequestDto)))
                        .andExpect(status().isCreated())
                        .andReturn();

        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), BakeryResponseDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @SneakyThrows
    void shouldUpdateBakery() {
        // given
        var expected = getBakeryUpdatedResponseDto();
        var bakeryRequestDto = getBakeryUpdateRequestDto();
        // when
        var mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.put("/bakeries/1")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(bakeryRequestDto)))
                        .andExpect(status().isOk())
                        .andReturn();
        var result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(), BakeryResponseDto.class);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
