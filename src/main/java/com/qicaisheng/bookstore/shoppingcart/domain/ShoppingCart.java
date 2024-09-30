package com.qicaisheng.bookstore.shoppingcart.domain;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.Currency;
import com.qicaisheng.bookstore.book.domain.Price;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ShoppingCart {
    private String userId;
    private List<ShoppingBook> books;

    public Price getTotalPrice() {
        BigDecimal totalValue = BigDecimal.ZERO;

        for (ShoppingBook shoppingBook : books) {
            Book book = shoppingBook.getBook();
            BigDecimal bookPriceValue = book.getPrice().getValue();
            int quantity = shoppingBook.getQuantity();
            totalValue = totalValue.add(bookPriceValue.multiply(BigDecimal.valueOf(quantity)));
        }

        Currency defaultCurrency = Currency.CNY;
        boolean sameCurrency = books.stream()
                .allMatch(shoppingBook -> shoppingBook.getBook().getPrice().getCurrency().equals(defaultCurrency));

        if (!sameCurrency) {
            throw new IllegalArgumentException("All books must use CNY currency.");
        }

        return new Price(totalValue, defaultCurrency);
    }
}
