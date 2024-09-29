package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookRepository;
import com.qicaisheng.bookstore.book.infrastructure.BookJPARepository;
import com.qicaisheng.bookstore.common.PageDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookJPARepository bookJPARepository;

    @BeforeEach
    void setUp() {
        bookJPARepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        bookJPARepository.deleteAll();
    }

    @Test
    void shouldSaveAndList() {
        Book book = TestBookFactory.getBook();

        Book savedBook = bookRepository.save(book);

        assertSame(savedBook, book);

        PageDTO<Book> response = bookRepository.list(0, 10);
        assertNotNull(response.getContent());
        List<Book> books = response.getContent();
        assertEquals(1, books.size());
        assertEquals(book.getTitle(), books.get(0).getTitle());

        assertEquals(0, response.getPageNumber());
        assertEquals(10, response.getPageSize());
        assertEquals(1, response.getTotalPages());
        assertEquals(1, response.getTotalElements());
    }
}