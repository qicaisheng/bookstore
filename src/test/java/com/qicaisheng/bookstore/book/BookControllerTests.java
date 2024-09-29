package com.qicaisheng.bookstore.book;

import com.jayway.jsonpath.JsonPath;
import com.qicaisheng.bookstore.book.infrastructure.BookJPARepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private BookJPARepository bookJPARepository;

    @BeforeEach
    void setUp() {
        bookJPARepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        bookJPARepository.deleteAll();
    }


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

    @Test
    void shouldUpdateBook() throws Exception {
        String responseContent = mockMvc.perform(MockMvcRequestBuilders.post("/books/")
                        .content("{\"title\":\"book 1\",\"author\":\"author 1\",\"price\":{\"value\": 100.00, \"currency\":\"CNY\"},\"category\":\"Business\"}")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String bookId = JsonPath.parse(responseContent).read("$.id");

        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + bookId)
                        .content("{\"title\":\"updated book\",\"author\":\"updated author\",\"price\":{\"value\": 150.00, \"currency\":\"CNY\"},\"category\":\"Science\"}")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.title").value("updated book"))
                .andExpect(jsonPath("$.author").value("updated author"))
                .andExpect(jsonPath("$.price.value").value(150.00))
                .andExpect(jsonPath("$.price.currency").value("CNY"))
                .andExpect(jsonPath("$.category").value("Science"));

        mockMvc.perform(MockMvcRequestBuilders.get("/books/?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].title").value("updated book"))
                .andExpect(jsonPath("$.content[0].author").value("updated author"))
                .andExpect(jsonPath("$.content[0].price.value").value(150.00))
                .andExpect(jsonPath("$.content[0].price.currency").value("CNY"))
                .andExpect(jsonPath("$.content[0].category").value("Science"));
    }

}
