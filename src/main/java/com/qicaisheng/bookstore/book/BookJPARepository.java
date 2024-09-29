package com.qicaisheng.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJPARepository extends JpaRepository<BookPO, String> {
}
