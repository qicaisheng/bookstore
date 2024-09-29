package com.qicaisheng.bookstore.shoppingcart;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookRepository;
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
    void shouldCreateShoppingCart() {
        String userId = "user123";
        ShoppingBookRequestDTO bookRequest1 = new ShoppingBookRequestDTO("book1", 2);
        ShoppingBookRequestDTO bookRequest2 = new ShoppingBookRequestDTO("book2", 3);
        ShoppingCartCreateRequestDTO requestDTO = new ShoppingCartCreateRequestDTO(userId, Arrays.asList(bookRequest1, bookRequest2));

        Book book1 = new Book();
        book1.setId("book1");
        book1.setTitle("Book Title 1");

        Book book2 = new Book();
        book2.setId("book2");
        book2.setTitle("Book Title 2");

        when(bookRepository.findAllByIds(Arrays.asList("book1", "book2"))).thenReturn(Arrays.asList(book1, book2));
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenAnswer(invocation -> invocation.getArgument(0));


        ShoppingCart shoppingCart = shoppingCartService.create(requestDTO);


        assertNotNull(shoppingCart);
        assertEquals(userId, shoppingCart.getUserId());
        assertEquals(2, shoppingCart.getBooks().size());
        assertEquals("Book Title 1", shoppingCart.getBooks().get(0).getBook().getTitle());
        assertEquals(2, shoppingCart.getBooks().get(0).getNumber());
        assertEquals("Book Title 2", shoppingCart.getBooks().get(1).getBook().getTitle());
        assertEquals(3, shoppingCart.getBooks().get(1).getNumber());

        verify(bookRepository).findAllByIds(Arrays.asList("book1", "book2"));
        verify(shoppingCartRepository).save(any(ShoppingCart.class));
    }
}
