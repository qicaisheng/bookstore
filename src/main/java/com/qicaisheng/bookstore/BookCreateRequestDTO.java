package com.qicaisheng.bookstore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequestDTO {
    private String title;
    private String author;
    private Price price;
    private BookCategory category;
}
