package com.qicaisheng.bookstore.shoppingcart.domain;

import com.qicaisheng.bookstore.book.TestBookFactory;
import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.Currency;
import com.qicaisheng.bookstore.book.domain.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void shouldGetTotalPrice() {
        ShoppingCart shoppingCart = new ShoppingCart();
        String user1 = "user1";
        shoppingCart.setUserId(user1);
        Book book1 = TestBookFactory.buildBook("book1");
        Book book2 = TestBookFactory.buildBook("book2");
        ShoppingBook shoppingBook1 = new ShoppingBook(book1, 2);
        ShoppingBook shoppingBook2 = new ShoppingBook(book2, 3);
        shoppingCart.setBooks(Arrays.asList(shoppingBook1, shoppingBook2));

        Price totalPrice = shoppingCart.getTotalPrice();

        assertSame(totalPrice.getCurrency(), Currency.CNY);
        assertEquals(totalPrice.getValue(), new BigDecimal("499.95"));
    }

    @Test
    void shouldReturnZeroWhenGetTotalPriceGiveEmptyShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        String user1 = "user1";
        shoppingCart.setUserId(user1);
        shoppingCart.setBooks(new ArrayList<>());

        Price totalPrice = shoppingCart.getTotalPrice();

        assertSame(totalPrice.getCurrency(), Currency.CNY);
        assertEquals(totalPrice.getValue(), BigDecimal.ZERO);
    }
}