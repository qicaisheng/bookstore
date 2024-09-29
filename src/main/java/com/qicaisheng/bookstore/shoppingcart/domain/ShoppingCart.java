package com.qicaisheng.bookstore.shoppingcart.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShoppingCart {
    private String userId;
    private List<ShoppingBook> books;
}
