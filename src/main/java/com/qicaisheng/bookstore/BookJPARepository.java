package com.qicaisheng.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJPARepository extends JpaRepository<BookPO, String> {
}
