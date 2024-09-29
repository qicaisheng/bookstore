package com.qicaisheng.bookstore.shoppingcart;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;

    public ShoppingCart create(ShoppingCartCreateRequestDTO shoppingCartCreateRequestDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Map<String, Integer> bookIdNumberMap = shoppingCartCreateRequestDTO.getShoppingBooks().stream()
                .collect(Collectors.toMap(ShoppingBookRequestDTO::getBookId, ShoppingBookRequestDTO::getNumber));
        List<String> bookIds = shoppingCartCreateRequestDTO.getShoppingBooks().stream().map(ShoppingBookRequestDTO::getBookId).toList();
        List<Book> books = bookRepository.findAllByIds(bookIds);
        List<ShoppingBook> shoppingBooks = books.stream()
                .map(book -> new ShoppingBook(book, bookIdNumberMap.get(book.getId())))
                .toList();
        shoppingCart.setUserId(shoppingCartCreateRequestDTO.getUserId());
        shoppingCart.setBooks(shoppingBooks);
        return shoppingCartRepository.save(shoppingCart);
    }
}
