package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookRepository;
import com.qicaisheng.bookstore.book.infrastructure.BookCreateRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(BookCreateRequestDTO bookCreateRequestDTO) {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle(bookCreateRequestDTO.getTitle());
        book.setCategory(bookCreateRequestDTO.getCategory());
        book.setAuthor(bookCreateRequestDTO.getAuthor());
        book.setPrice(bookCreateRequestDTO.getPrice());

        return bookRepository.save(book);
    }

    public List<Book> list() {
        return bookRepository.list();
    }
}
