package com.qicaisheng.bookstore.shoppingcart.domain;

import com.qicaisheng.bookstore.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShoppingBook {
    private Book book;
    private int number;
}
