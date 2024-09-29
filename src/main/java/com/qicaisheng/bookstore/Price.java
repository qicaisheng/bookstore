package com.qicaisheng.bookstore;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Price {
    private BigDecimal value;
    private Currency currency;
}
