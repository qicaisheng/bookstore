package com.qicaisheng.bookstore;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

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
}
