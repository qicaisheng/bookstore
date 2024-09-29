package com.qicaisheng.bookstore.shoppingcart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingCartCreateRequestDTO {
    private String userId;
    private List<ShoppingBookRequestDTO> shoppingBooks;
}
