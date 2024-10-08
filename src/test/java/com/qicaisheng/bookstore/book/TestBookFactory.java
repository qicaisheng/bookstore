package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookCategory;
import com.qicaisheng.bookstore.common.Currency;
import com.qicaisheng.bookstore.common.Price;
import com.qicaisheng.bookstore.book.infrastructure.BookPO;

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

    public static Book buildBook(String id) {
        Book book = new Book();
        book.setId(id);
        book.setAuthor("Author");
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

    public static BookPO buildBookPo(String id) {
        BookPO bookPO = new BookPO();
        bookPO.setId(id);
        bookPO.setAuthor("Author 1");
        bookPO.setCategory(BookCategory.Business);
        bookPO.setTitle("Book Title");
        bookPO.setPriceValue(new BigDecimal("99.99"));
        bookPO.setPriceCurrency(Currency.CNY);

        return bookPO;
    }
}
