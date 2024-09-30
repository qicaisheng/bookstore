package com.qicaisheng.bookstore.shoppingcart;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for creating or updating a shopping cart")
public class ShoppingCartRequestDTO {
    @Schema(description = "The ID of the user associated with the shopping cart", example = "user1")
    @NotBlank(message = "UserId cannot be blank")
    private String userId;

    @Schema(description = "List of books in the shopping cart")
    @NotEmpty(message = "Shopping books list cannot be empty")
    private List<ShoppingBookRequestDTO> shoppingBooks;
}
