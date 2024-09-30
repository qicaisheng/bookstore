package com.qicaisheng.bookstore.shoppingcart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Request DTO for a book in the shopping cart")
public class ShoppingBookRequestDTO {
    @Schema(description = "The ID of the book", example = "book1")
    private String bookId;

    @Schema(description = "The quantity of the book", example = "2")
    private int quantity;
}
