package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.infrastructure.BookCreateRequestDTO;
import com.qicaisheng.bookstore.common.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public PageDTO<Book> list(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        return bookService.list(page, size);
    }

    @PostMapping
    public Book create(@RequestBody BookCreateRequestDTO bookCreateRequestDTO) {
        return bookService.save(bookCreateRequestDTO);
    }
}
