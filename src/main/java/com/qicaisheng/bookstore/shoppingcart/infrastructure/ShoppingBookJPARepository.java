package com.qicaisheng.bookstore.shoppingcart.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingBookJPARepository extends JpaRepository<ShoppingBookPO, String> {
    List<ShoppingBookPO> findByUserId(String userId);
    void deleteAllByUserId(String userId);
}
