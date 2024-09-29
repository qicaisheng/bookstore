package com.qicaisheng.bookstore.shoppingcart;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.infrastructure.BookConverter;
import com.qicaisheng.bookstore.book.infrastructure.BookJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class H2ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private final ShoppingBookJPARepository shoppingBookJPARepository;
    private final BookJPARepository bookJPARepository;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        shoppingBookJPARepository.deleteAll();
        List<ShoppingBookPO> shoppingBookPOs = shoppingCart.getBooks().stream()
                .map(shoppingBook -> {
                    ShoppingBookPO shoppingBookPO = new ShoppingBookPO();
                    shoppingBookPO.setUserId(shoppingCart.getUserId());
                    shoppingBookPO.setBookId(shoppingBook.getBook().getId());
                    shoppingBookPO.setNumber(shoppingBook.getNumber());
                    return shoppingBookPO;
                })
                .collect(Collectors.toList());

        shoppingBookJPARepository.saveAll(shoppingBookPOs);
        return shoppingCart;
    }

    @Override
    public ShoppingCart findByUserId(String userId) {
        List<ShoppingBookPO> shoppingBooks = shoppingBookJPARepository.findByUserId(userId);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userId);

        List<String> bookIds = shoppingBooks.stream()
                .map(ShoppingBookPO::getBookId)
                .collect(Collectors.toList());

        List<Book> books = bookJPARepository.findAllById(bookIds)
                .stream()
                .map(BookConverter::toEntity)
                .toList();

        List<ShoppingBook> shoppingBookList = books.stream()
                .map(book -> new ShoppingBook(book, shoppingBooks.stream()
                        .filter(shoppingBookPO -> shoppingBookPO.getBookId().equals(book.getId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Book not found in shopping cart"))
                        .getNumber()))
                .collect(Collectors.toList());

        shoppingCart.setBooks(shoppingBookList);
        return shoppingCart;    }
}
