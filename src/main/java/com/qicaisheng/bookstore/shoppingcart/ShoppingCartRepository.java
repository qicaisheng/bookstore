package com.qicaisheng.bookstore.shoppingcart;

public interface ShoppingCartRepository {
    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart findByUserId(String userId);
}
