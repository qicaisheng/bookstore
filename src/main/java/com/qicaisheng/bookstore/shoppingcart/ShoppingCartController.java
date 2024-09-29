package com.qicaisheng.bookstore.shoppingcart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/users/{userId}/shopping-cart")
    public ShoppingCart create(@PathVariable String userId,
                               @RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO) {
        shoppingCartRequestDTO.setUserId(userId);
        return shoppingCartService.create(shoppingCartRequestDTO);
    }
}
