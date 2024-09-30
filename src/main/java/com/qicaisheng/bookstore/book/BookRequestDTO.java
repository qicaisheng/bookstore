package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.domain.BookCategory;
import com.qicaisheng.bookstore.book.domain.Price;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDTO {
    @Schema(description = "Title of the book", example = "Effective Java")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Schema(description = "Author of the book", example = "Joshua Bloch")
    @NotBlank(message = "Author cannot be blank")
    private String author;

    @Schema(description = "Price details of the book")
    @NotNull(message = "Price cannot be null")
    @Valid
    private Price price;

    @Schema(description = "Category of the book", example = "Technology")
    @NotNull(message = "Category cannot be null")
    private BookCategory category;
}
