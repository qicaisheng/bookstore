package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookCategory;
import com.qicaisheng.bookstore.book.domain.Currency;
import com.qicaisheng.bookstore.book.domain.Price;

import java.math.BigDecimal;

public class TestBookFactory {
    public static Book getBook() {
        Book book = new Book();
        book.setId("1");
        book.setAuthor("Author 1");
        book.setCategory(BookCategory.Business);
        book.setTitle("Book Title");
        book.setPrice(new Price(new BigDecimal("99.99"), Currency.CNY));

        return book;
    }

    public static BookPO getBookPO() {
        BookPO bookPO = new BookPO();
        bookPO.setId("1");
        bookPO.setAuthor("Author 1");
        bookPO.setCategory(BookCategory.Business);
        bookPO.setTitle("Book Title");
        bookPO.setPriceValue(new BigDecimal("99.99"));
        bookPO.setPriceCurrency(Currency.CNY);

        return bookPO;
    }
}
