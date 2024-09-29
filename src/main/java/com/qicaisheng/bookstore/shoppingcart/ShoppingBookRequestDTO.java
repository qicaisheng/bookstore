package com.qicaisheng.bookstore.shoppingcart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingBookRequestDTO {
    private String bookId;
    private int number;
}
