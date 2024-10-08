package com.qicaisheng.bookstore.shoppingcart;

import com.qicaisheng.bookstore.book.TestBookFactory;
import com.qicaisheng.bookstore.book.infrastructure.BookJPARepository;
import com.qicaisheng.bookstore.book.infrastructure.BookPO;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCartRepository;
import com.qicaisheng.bookstore.shoppingcart.infrastructure.ShoppingBookJPARepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private BookJPARepository bookJPARepository;

    @Autowired
    private ShoppingBookJPARepository shoppingBookJPARepository;

    @BeforeEach
    void setUp() {
        bookJPARepository.deleteAll();
        shoppingBookJPARepository.deleteAll();

        BookPO bookPO1 = TestBookFactory.buildBookPo("book1");
        bookJPARepository.save(bookPO1);

        BookPO bookPO2 = TestBookFactory.buildBookPo("book2");
        bookJPARepository.save(bookPO2);
    }

    @AfterEach
    void tearDown() {
        bookJPARepository.deleteAll();
        shoppingBookJPARepository.deleteAll();
    }

    @Test
    void shouldCreateAndUpdateShoppingCartUsingSave() throws Exception {
        String userId = "user1";

        mockMvc.perform(post("/users/{userId}/shopping-cart", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"shoppingBooks\":[{\"bookId\":\"book1\",\"quantity\":2},{\"bookId\":\"book2\",\"quantity\":3}]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.books.length()").value(2))
                .andExpect(jsonPath("$.books[0].book.id").value("book1"))
                .andExpect(jsonPath("$.books[0].book.title").value("Book Title"))
                .andExpect(jsonPath("$.books[0].quantity").value(2))
                .andExpect(jsonPath("$.books[1].book.id").value("book2"))
                .andExpect(jsonPath("$.books[1].book.title").value("Book Title"))
                .andExpect(jsonPath("$.books[1].quantity").value(3))
                .andExpect(jsonPath("$.totalPrice.value").value(499.95))
                .andExpect(jsonPath("$.totalPrice.currency").value("CNY"));

        mockMvc.perform(post("/users/{userId}/shopping-cart", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"shoppingBooks\":[{\"bookId\":\"book1\",\"quantity\":3},{\"bookId\":\"book2\",\"quantity\":4}]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.books.length()").value(2))
                .andExpect(jsonPath("$.books[0].book.id").value("book1"))
                .andExpect(jsonPath("$.books[0].book.title").value("Book Title"))
                .andExpect(jsonPath("$.books[0].quantity").value(3))
                .andExpect(jsonPath("$.books[1].book.id").value("book2"))
                .andExpect(jsonPath("$.books[1].book.title").value("Book Title"))
                .andExpect(jsonPath("$.books[1].quantity").value(4))
                .andExpect(jsonPath("$.totalPrice.value").value(699.93))
                .andExpect(jsonPath("$.totalPrice.currency").value("CNY"));

        mockMvc.perform(get("/users/{userId}/shopping-cart", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.books.length()").value(2))
                .andExpect(jsonPath("$.books[0].book.id").value("book1"))
                .andExpect(jsonPath("$.books[0].book.title").value("Book Title"))
                .andExpect(jsonPath("$.books[0].quantity").value(3))
                .andExpect(jsonPath("$.books[1].book.id").value("book2"))
                .andExpect(jsonPath("$.books[1].book.title").value("Book Title"))
                .andExpect(jsonPath("$.books[1].quantity").value(4))
                .andExpect(jsonPath("$.totalPrice.value").value(699.93))
                .andExpect(jsonPath("$.totalPrice.currency").value("CNY"));
    }

    @Test
    void shouldFailedWhenSaveGivenWithDuplicatedBookId() throws Exception {
        String userId = "user1";

        mockMvc.perform(post("/users/{userId}/shopping-cart", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"shoppingBooks\":[{\"bookId\":\"book1\",\"quantity\":2},{\"bookId\":\"book1\",\"quantity\":3}]}"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.bookId").value("Book IDs must be unique: book1"));
    }
}
