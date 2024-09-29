package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.Price;

public class BookConverter {
    public static Book toEntity(BookPO bookPO) {
        Book book = new Book();
        book.setId(bookPO.getId());
        book.setAuthor(bookPO.getAuthor());
        book.setCategory(bookPO.getCategory());
        book.setTitle(bookPO.getTitle());
        book.setPrice(new Price(bookPO.getPriceValue(), bookPO.getPriceCurrency()));
        return book;
    }

    public static BookPO toPO(Book book) {
        BookPO bookPO = new BookPO();
        bookPO.setId(book.getId());
        bookPO.setTitle(book.getTitle());
        bookPO.setAuthor(book.getAuthor());
        bookPO.setCategory(book.getCategory());
        bookPO.setPriceValue(book.getPrice().getValue());
        bookPO.setPriceCurrency(book.getPrice().getCurrency());
        return bookPO;
    }
}
