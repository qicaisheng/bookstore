package com.qicaisheng.bookstore.shoppingcart;

import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCart;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Shopping Cart, Checkout", description = "Operations related to shopping carts and checkout")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Save a new shopping cart for a user, And return saved ShoppingCart which with total prices.",
            description = "Saves a shopping cart based on the provided user ID and shopping cart request data. And return saved ShoppingCart which with total prices.")
    @PostMapping("/users/{userId}/shopping-cart")
    public ShoppingCart save(
            @Parameter(description = "The ID of the user for whom the shopping cart is being created")
            @PathVariable String userId,
            @RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO) {
        shoppingCartRequestDTO.setUserId(userId);
        return shoppingCartService.save(shoppingCartRequestDTO);
    }

    @Operation(summary = "Retrieve the shopping cart and calculate the total price of the items for a user",
            description = "Fetches the shopping cart associated with the provided user ID.")
    @GetMapping("/users/{userId}/shopping-cart")
    public ShoppingCart get(
            @Parameter(description = "The ID of the user whose shopping cart is to be retrieved")
            @PathVariable String userId) {
        return shoppingCartService.findByUserId(userId);
    }
}
