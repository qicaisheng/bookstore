package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookCategory;
import com.qicaisheng.bookstore.book.domain.Currency;
import com.qicaisheng.bookstore.book.infrastructure.BookConverter;
import com.qicaisheng.bookstore.book.infrastructure.BookPO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookConverterTest {

    @Test
    void testToEntity() {
        BookPO bookPO = TestBookFactory.getBookPO();

        Book book = BookConverter.toEntity(bookPO);

        assertEquals("1", book.getId());
        assertEquals("Author 1", book.getAuthor());
        assertEquals(BookCategory.Business, book.getCategory());
        assertEquals("Book Title", book.getTitle());
        assertEquals(new BigDecimal("99.99"), book.getPrice().getValue());
        assertEquals(Currency.CNY, book.getPrice().getCurrency());
    }

    @Test
    void testToPO() {
        Book book = TestBookFactory.getBook();

        BookPO bookPO = BookConverter.toPO(book);

        assertEquals("1", bookPO.getId());
        assertEquals("Author 1", bookPO.getAuthor());
        assertEquals(BookCategory.Business, bookPO.getCategory());
        assertEquals("Book Title", bookPO.getTitle());
        assertEquals(new BigDecimal("99.99"), bookPO.getPriceValue());
        assertEquals(book.getPrice().getCurrency(), bookPO.getPriceCurrency());
    }
}