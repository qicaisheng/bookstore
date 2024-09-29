package com.qicaisheng.bookstore.book.infrastructure;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class H2BookRepositoryImpl implements BookRepository {

    private final BookJPARepository bookJPARepository;

    @Override
    public Book save(Book book) {
        BookPO bookPO = BookConverter.toPO(book);
        bookJPARepository.save(bookPO);
        return book;
    }

    @Override
    public List<Book> list() {
        List<BookPO> all = bookJPARepository.findAll();
        return all.stream().map(BookConverter::toEntity).toList();
    }
}
