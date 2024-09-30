package com.qicaisheng.bookstore.shoppingcart;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookRepository;
import com.qicaisheng.bookstore.shoppingcart.dto.ShoppingBookRequestDTO;
import com.qicaisheng.bookstore.shoppingcart.dto.ShoppingCartRequestDTO;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCart;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveShoppingCart() {
        String userId = "user1";
        ShoppingCartRequestDTO requestDTO = new ShoppingCartRequestDTO();
        requestDTO.setUserId(userId);

        ShoppingBookRequestDTO bookRequest1 = new ShoppingBookRequestDTO("book1", 2);
        ShoppingBookRequestDTO bookRequest2 = new ShoppingBookRequestDTO("book2", 3);
        requestDTO.setShoppingBooks(Arrays.asList(bookRequest1, bookRequest2));


        Book book1 = new Book();
        book1.setId("book1");
        book1.setTitle("Book Title 1");

        Book book2 = new Book();
        book2.setId("book2");
        book2.setTitle("Book Title 2");

        when(bookRepository.findAllByIds(anyList())).thenReturn(Arrays.asList(book1, book2));
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenAnswer(invocation -> invocation.getArgument(0));


        ShoppingCart updatedCart = shoppingCartService.save(requestDTO);


        assertNotNull(updatedCart);
        assertEquals(userId, updatedCart.getUserId());
        assertEquals(2, updatedCart.getBooks().size());
        assertEquals(2, updatedCart.getBooks().get(0).getQuantity());
        assertEquals(3, updatedCart.getBooks().get(1).getQuantity());

        verify(bookRepository).findAllByIds(anyList());
        verify(shoppingCartRepository).save(updatedCart);
    }
}
