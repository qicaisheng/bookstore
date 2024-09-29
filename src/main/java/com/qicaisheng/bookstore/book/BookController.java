package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.infrastructure.BookCreateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> list() {
        return bookService.list();
    }

    @PostMapping
    public Book create(@RequestBody BookCreateRequestDTO bookCreateRequestDTO) {
        return bookService.save(bookCreateRequestDTO);
    }
}
