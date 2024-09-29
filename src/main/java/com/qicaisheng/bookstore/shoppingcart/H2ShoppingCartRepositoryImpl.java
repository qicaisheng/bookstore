package com.qicaisheng.bookstore.shoppingcart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class H2ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private final ShoppingBookJPARepository shoppingBookJPARepository;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
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
        return null;
    }
}
