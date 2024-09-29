package com.qicaisheng.bookstore.book.domain;

import com.qicaisheng.bookstore.common.PageDTO;

public interface BookRepository {
    Book save(Book book);

    PageDTO<Book> list(int page, int size);

    Book update(Book book);
}
