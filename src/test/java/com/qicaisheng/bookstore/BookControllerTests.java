package com.qicaisheng.bookstore;

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
    void shouldListAvailableBooksAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/books/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"001\",\"title\":\"book 1\",\"author\":\"author 1\",\"price\":{\"value\": 100.00, \"currency\":\"CNY\"},\"category\":\"Business\"}]"));
    }

    @Test
    void shouldCreateBooksAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/books/")
                        .content("{\"title\":\"book 1\",\"author\":\"author 1\",\"price\":{\"value\": 100.00, \"currency\":\"CNY\"},\"category\":\"Business\"}")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.title").value("book 1"))
                .andExpect(jsonPath("$.author").value("author 1"))
                .andExpect(jsonPath("$.price.value").value(100.00))
                .andExpect(jsonPath("$.price.currency").value("CNY"))
                .andExpect(jsonPath("$.category").value("Business"));    }

}
