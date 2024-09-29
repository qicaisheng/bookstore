package com.qicaisheng.bookstore.book.domain;

import com.qicaisheng.bookstore.common.Page;

import java.util.List;

public interface BookRepository {
    Book save(Book book);

    Page<Book> list(int page, int size);

    Book update(Book book);

    List<Book> findAllByIds(List<String> bookIds);
}
