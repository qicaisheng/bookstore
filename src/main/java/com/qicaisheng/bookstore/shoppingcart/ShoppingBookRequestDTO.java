package com.qicaisheng.bookstore.shoppingcart;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Request DTO for a book in the shopping cart")
public class ShoppingBookRequestDTO {
    @Schema(description = "The ID of the book", example = "book1")
    @NotBlank(message = "BookId cannot be blank")
    private String bookId;

    @Schema(description = "The quantity of the book", example = "2")
    @Positive(message = "Quantity must be greater than 0")
    private int quantity;
}
