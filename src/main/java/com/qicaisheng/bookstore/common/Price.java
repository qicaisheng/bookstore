package com.qicaisheng.bookstore.common;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Price {
    @Positive(message = "Value must be greater than 0")
    private BigDecimal value;

    @NotNull(message = "Currency cannot be null")
    private Currency currency;
}
