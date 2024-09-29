package com.qicaisheng.bookstore.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateAndList() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/books/")
                        .content("{\"title\":\"book 1\",\"author\":\"author 1\",\"price\":{\"value\": 100.00, \"currency\":\"CNY\"},\"category\":\"Business\"}")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.title").value("book 1"))
                .andExpect(jsonPath("$.author").value("author 1"))
                .andExpect(jsonPath("$.price.value").value(100.00))
                .andExpect(jsonPath("$.price.currency").value("CNY"))
                .andExpect(jsonPath("$.category").value("Business"));

        mockMvc.perform(MockMvcRequestBuilders.get("/books/?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].title").value("book 1"))
                .andExpect(jsonPath("$.content[0].author").value("author 1"))
                .andExpect(jsonPath("$.content[0].price.value").value(100.00))
                .andExpect(jsonPath("$.content[0].price.currency").value("CNY"))
                .andExpect(jsonPath("$.content[0].category").value("Business"))
                .andExpect(jsonPath("$.pageNumber").value(0))
                .andExpect(jsonPath("$.pageSize").value(10))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1));
    }
}
