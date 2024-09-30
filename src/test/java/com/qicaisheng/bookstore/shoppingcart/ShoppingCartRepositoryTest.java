package com.qicaisheng.bookstore.shoppingcart;

import com.qicaisheng.bookstore.book.TestBookFactory;
import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.infrastructure.BookJPARepository;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingBook;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCart;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCartRepository;
import com.qicaisheng.bookstore.shoppingcart.infrastructure.ShoppingBookJPARepository;
import com.qicaisheng.bookstore.shoppingcart.infrastructure.ShoppingBookPO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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
    void shouldSaveShoppingCart() {
        String userId1 = "user1";
        Book book = TestBookFactory.buildBook("book1");
        List<ShoppingBook> user1shoppingBooks = new ArrayList<>();
        user1shoppingBooks.add(new ShoppingBook(book, 2));
        ShoppingCart user1ShoppingCart = new ShoppingCart(userId1, user1shoppingBooks);
        shoppingCartRepository.save(user1ShoppingCart);

        String userId2 = "user2";
        List<ShoppingBook> user2shoppingBooks = new ArrayList<>();
        user2shoppingBooks.add(new ShoppingBook(book, 3));
        ShoppingCart user2ShoppingCart = new ShoppingCart(userId2, user2shoppingBooks);
        shoppingCartRepository.save(user2ShoppingCart);

        List<ShoppingBookPO> user1ShoppingBookPO = shoppingBookJPARepository.findByUserId(userId1);
        assertEquals(1, user1ShoppingBookPO.size());
        assertEquals("book1", user1ShoppingBookPO.get(0).getBookId());
        assertEquals(userId1, user1ShoppingBookPO.get(0).getUserId());
        assertEquals(2, user1ShoppingBookPO.get(0).getQuantity());

        List<ShoppingBookPO> user2ShoppingBookPO = shoppingBookJPARepository.findByUserId(userId2);
        assertEquals(1, user2ShoppingBookPO.size());
        assertEquals("book1", user2ShoppingBookPO.get(0).getBookId());
        assertEquals(userId2, user2ShoppingBookPO.get(0).getUserId());
        assertEquals(3, user2ShoppingBookPO.get(0).getQuantity());
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
        shoppingBookPO.setQuantity(3);
        shoppingBookJPARepository.save(shoppingBookPO);

        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userID);

        assertNotNull(shoppingCart);
        assertSame(shoppingCart.getUserId(), userID);
        assertNotNull(shoppingCart.getBooks());
        assertEquals(1, shoppingCart.getBooks().size());
        assertEquals(3, shoppingCart.getBooks().get(0).getQuantity());
        assertEquals("book1", shoppingCart.getBooks().get(0).getBook().getId());
        assertEquals("Book Title", shoppingCart.getBooks().get(0).getBook().getTitle());
    }
}