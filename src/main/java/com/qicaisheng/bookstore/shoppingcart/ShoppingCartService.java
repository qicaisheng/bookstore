package com.qicaisheng.bookstore.shoppingcart;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookRepository;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingBook;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCart;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;

    @Transactional
    public ShoppingCart save(ShoppingCartRequestDTO shoppingCartRequestDTO) {
        Map<String, Integer> bookIdQuantityMap = shoppingCartRequestDTO.getShoppingBooks().stream()
                .collect(Collectors.toMap(ShoppingBookRequestDTO::getBookId, ShoppingBookRequestDTO::getQuantity));
        List<String> bookIds = new ArrayList<>(bookIdQuantityMap.keySet());
        List<Book> books = bookRepository.findAllByIds(bookIds);
        List<ShoppingBook> shoppingBooks = books.stream()
                .map(book -> new ShoppingBook(book, bookIdQuantityMap.get(book.getId())))
                .toList();
        ShoppingCart shoppingCart = new ShoppingCart(shoppingCartRequestDTO.getUserId(), shoppingBooks);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart findByUserId(String userId) {
        return shoppingCartRepository.findByUserId(userId);
    }
}
