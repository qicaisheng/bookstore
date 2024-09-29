package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookRepository;
import com.qicaisheng.bookstore.common.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book save(BookCreateRequestDTO bookCreateRequestDTO) {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle(bookCreateRequestDTO.getTitle());
        book.setCategory(bookCreateRequestDTO.getCategory());
        book.setAuthor(bookCreateRequestDTO.getAuthor());
        book.setPrice(bookCreateRequestDTO.getPrice());

        return bookRepository.save(book);
    }

    @Transactional
    public Book update(String id, BookUpdateRequestDTO bookUpdateRequestDTO) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(bookUpdateRequestDTO.getTitle());
        book.setCategory(bookUpdateRequestDTO.getCategory());
        book.setAuthor(bookUpdateRequestDTO.getAuthor());
        book.setPrice(bookUpdateRequestDTO.getPrice());
        return bookRepository.update(book);
    }

    public Page<Book> list(int page, int size) {
        return bookRepository.list(page, size);
    }
}
