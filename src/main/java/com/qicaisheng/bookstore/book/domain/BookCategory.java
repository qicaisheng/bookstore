package com.qicaisheng.bookstore.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BookCategory {
    Business("商业"),
    Science("科学"),
    Technology("技术");

    private final String name;
}
