package com.qicaisheng.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

}
