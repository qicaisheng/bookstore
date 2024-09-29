package com.qicaisheng.bookstore.shoppingcart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/users/{userId}/shopping-cart")
    public ShoppingCart save(@PathVariable String userId,
                               @RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO) {
        shoppingCartRequestDTO.setUserId(userId);
        return shoppingCartService.save(shoppingCartRequestDTO);
    }

    @GetMapping("/users/{userId}/shopping-cart")
    public ShoppingCart get(@PathVariable String userId) {
        return shoppingCartService.findByUserId(userId);
    }
}
