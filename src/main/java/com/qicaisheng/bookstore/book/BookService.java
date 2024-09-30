package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.dto.BookCreateRequestDTO;
import com.qicaisheng.bookstore.book.dto.BookRequestDTO;
import com.qicaisheng.bookstore.book.dto.BookUpdateRequestDTO;
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
        String id = UUID.randomUUID().toString();
        Book book =  toEntity(bookCreateRequestDTO, id);
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(String id, BookUpdateRequestDTO bookUpdateRequestDTO) {
        Book book = toEntity(bookUpdateRequestDTO, id);
        return bookRepository.update(book);
    }

    public Page<Book> list(int page, int size) {
        return bookRepository.list(page, size);
    }

    private static Book toEntity(BookRequestDTO bookRequestDTO, String id) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(bookRequestDTO.getTitle());
        book.setCategory(bookRequestDTO.getCategory());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setPrice(bookRequestDTO.getPrice());
        return book;
    }
}
