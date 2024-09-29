package com.qicaisheng.bookstore.shoppingcart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartRequestDTO {
    private String userId;
    private List<ShoppingBookRequestDTO> shoppingBooks;
}
