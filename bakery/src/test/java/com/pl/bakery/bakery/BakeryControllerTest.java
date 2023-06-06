package com.pl.bakery.bakery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pl.bakery.TestContainer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class BakeryControllerTest extends TestContainer {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;


    @Test
    void shouldGetAllBakeries() throws Exception {
        // given
        BakeryEntity expected = new BakeryEntity(1L, "TestName", "TestAddress", "TestLocation");
        // when
        MvcResult mvcResult =
                mockMvc
                        .perform(MockMvcRequestBuilders.get("/bakeries"))
                        .andExpect(status().isOk())
                        .andReturn();
        List<BakeryEntity> result =
                objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, BakeryEntity.class));
        // then
        AssertionsForClassTypes.assertThat(result.get(0))
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}