package com.qicaisheng.bookstore.shoppingcart.infrastructure;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.infrastructure.BookConverter;
import com.qicaisheng.bookstore.book.infrastructure.BookJPARepository;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingBook;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCart;
import com.qicaisheng.bookstore.shoppingcart.domain.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class H2ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private final ShoppingBookJPARepository shoppingBookJPARepository;
    private final BookJPARepository bookJPARepository;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        shoppingBookJPARepository.deleteAllByUserId(shoppingCart.getUserId());

        List<ShoppingBookPO> shoppingBookPOs = shoppingCart.getBooks().stream()
                .map(shoppingBook -> {
                    ShoppingBookPO shoppingBookPO = new ShoppingBookPO();
                    shoppingBookPO.setUserId(shoppingCart.getUserId());
                    shoppingBookPO.setBookId(shoppingBook.getBook().getId());
                    shoppingBookPO.setQuantity(shoppingBook.getQuantity());
                    return shoppingBookPO;
                })
                .collect(Collectors.toList());

        shoppingBookJPARepository.saveAll(shoppingBookPOs);
        return shoppingCart;
    }

    @Override
    public ShoppingCart findByUserId(String userId) {
        List<ShoppingBookPO> shoppingBooks = shoppingBookJPARepository.findByUserId(userId);
        List<String> bookIds = shoppingBooks.stream()
                .map(ShoppingBookPO::getBookId)
                .collect(Collectors.toList());

        Map<String, Book> bookIdAndBookMap = bookJPARepository.findAllById(bookIds)
                .stream()
                .map(BookConverter::toEntity)
                .collect(Collectors.toMap(Book::getId, book -> book));

        List<ShoppingBook> shoppingBookList = shoppingBooks.stream()
                .map(shoppingBookPO -> new ShoppingBook(bookIdAndBookMap.get(shoppingBookPO.getBookId()), shoppingBookPO.getQuantity()))
                .toList();

        return new ShoppingCart(userId, shoppingBookList);
    }
}
