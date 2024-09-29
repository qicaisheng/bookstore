package com.qicaisheng.bookstore;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books/")
@RequiredArgsConstructor
public class BookController {

    private List<Book> books = new ArrayList<>();

    @GetMapping
    public List<Book> list() {
        return books;
    }

    @PostMapping
    public Book create(@RequestBody BookCreateRequestDTO bookCreateRequestDTO) {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle(bookCreateRequestDTO.getTitle());
        book.setCategory(bookCreateRequestDTO.getCategory());
        book.setAuthor(bookCreateRequestDTO.getAuthor());
        book.setPrice(bookCreateRequestDTO.getPrice());
        books.add(book);
        return book;
    }
}
