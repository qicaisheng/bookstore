package com.qicaisheng.bookstore.book.domain;

import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> list();
}
