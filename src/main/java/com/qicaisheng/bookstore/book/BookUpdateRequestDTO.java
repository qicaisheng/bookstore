package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.BookCategory;
import com.qicaisheng.bookstore.book.domain.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateRequestDTO {
    private String title;
    private String author;
    private Price price;
    private BookCategory category;
}
