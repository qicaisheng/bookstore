package com.qicaisheng.bookstore.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Price {
    private BigDecimal value;
    private Currency currency;
}
