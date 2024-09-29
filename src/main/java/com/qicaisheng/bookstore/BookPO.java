package com.qicaisheng.bookstore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "book")
public class BookPO {
    @Id
    private String id;

    private String title;
    private String author;
    private BigDecimal priceValue;
    private Currency priceCurrency;
    private BookCategory category;
}
