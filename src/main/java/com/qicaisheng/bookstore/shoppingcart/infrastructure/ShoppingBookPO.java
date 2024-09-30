package com.qicaisheng.bookstore.shoppingcart.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "shopping_book")
public class ShoppingBookPO {
    @Id
    private String id;
    private String userId;
    private String bookId;
    private int quantity;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
