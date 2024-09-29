package com.qicaisheng.bookstore.book.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJPARepository extends JpaRepository<BookPO, String> {
}
