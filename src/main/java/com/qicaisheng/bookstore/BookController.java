package com.qicaisheng.bookstore;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books/")
@RequiredArgsConstructor
public class BookController {

    @GetMapping
    public List<Book> list() {
        Book book1 = new Book();
        book1.setId("001");
        book1.setTitle("book 1");
        book1.setAuthor("author 1");
        book1.setCategory(BookCategory.Business);
        book1.setPrice(new Price(new BigDecimal("100.00"), Currency.CNY));
        return List.of(book1);
    }

    @PostMapping
    public Book create(@RequestBody BookCreateRequestDTO bookCreateRequestDTO) {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle(bookCreateRequestDTO.getTitle());
        book.setCategory(bookCreateRequestDTO.getCategory());
        book.setAuthor(bookCreateRequestDTO.getAuthor());
        book.setPrice(bookCreateRequestDTO.getPrice());
        return book;
    }
}
