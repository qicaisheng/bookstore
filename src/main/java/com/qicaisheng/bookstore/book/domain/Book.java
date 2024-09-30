package com.qicaisheng.bookstore.book.domain;

import com.qicaisheng.bookstore.common.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private String id;
    private String title;
    private String author;
    private Price price;
    private BookCategory category;
}
