package com.qicaisheng.bookstore.shoppingcart.domain;

public interface ShoppingCartRepository {
    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart findByUserId(String userId);
}
