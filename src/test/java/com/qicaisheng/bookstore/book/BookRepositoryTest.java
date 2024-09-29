package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveAndList() {
        Book book = TestBookFactory.getBook();

        Book savedBook = bookRepository.save(book);

        assertSame(savedBook, book);

        List<Book> books = bookRepository.list();
        assertEquals(1, books.size());
        assertEquals(book.getTitle(), books.get(0).getTitle());
    }
}