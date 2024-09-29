package com.qicaisheng.bookstore.shoppingcart;

import com.qicaisheng.bookstore.book.TestBookFactory;
import com.qicaisheng.bookstore.book.infrastructure.BookJPARepository;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCart;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCartRepository;
import com.qicaisheng.bookstore.shoppingcart.infrastructure.ShoppingBookJPARepository;
import com.qicaisheng.bookstore.shoppingcart.infrastructure.ShoppingBookPO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingCartRepositoryTest {

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
    }

    @AfterEach
    void tearDown() {
        bookJPARepository.deleteAll();
        shoppingBookJPARepository.deleteAll();
    }

    @Test
    void shouldReturnEmptyShoppingCardWhenFindByUserIdGivenNoBookInCart() {
        String userID = "user1";
        ShoppingCart emptyShoppingCart = shoppingCartRepository.findByUserId(userID);

        assertNotNull(emptyShoppingCart);
        assertSame(emptyShoppingCart.getUserId(), userID);
        assertNotNull(emptyShoppingCart.getBooks());
        assertEquals(0, emptyShoppingCart.getBooks().size());
    }

    @Test
    void shouldReturnShoppingCardWhenFindByUserIdGivenBookInCart() {
        bookJPARepository.save(TestBookFactory.buildBookPo("book1"));

        String userID = "user1";
        ShoppingBookPO shoppingBookPO = new ShoppingBookPO();
        shoppingBookPO.setUserId(userID);
        shoppingBookPO.setBookId("book1");
        shoppingBookPO.setNumber(3);
        shoppingBookJPARepository.save(shoppingBookPO);

        ShoppingCart emptyShoppingCart = shoppingCartRepository.findByUserId(userID);

        assertNotNull(emptyShoppingCart);
        assertSame(emptyShoppingCart.getUserId(), userID);
        assertNotNull(emptyShoppingCart.getBooks());
        assertEquals(1, emptyShoppingCart.getBooks().size());
        assertEquals(3, emptyShoppingCart.getBooks().get(0).getNumber());
        assertEquals("book1", emptyShoppingCart.getBooks().get(0).getBook().getId());
        assertEquals("Book Title", emptyShoppingCart.getBooks().get(0).getBook().getTitle());
    }
}