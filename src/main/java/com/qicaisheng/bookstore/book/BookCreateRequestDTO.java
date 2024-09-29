package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.BookCategory;
import com.qicaisheng.bookstore.book.domain.Price;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Data Transfer Object for creating a new book")
public class BookCreateRequestDTO {
    @Schema(description = "Title of the book", example = "Effective Java")
    private String title;

    @Schema(description = "Author of the book", example = "Joshua Bloch")
    private String author;

    @Schema(description = "Price details of the book")
    private Price price;

    @Schema(description = "Category of the book", example = "Technology")
    private BookCategory category;
}
