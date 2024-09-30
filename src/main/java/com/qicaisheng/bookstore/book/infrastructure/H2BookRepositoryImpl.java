package com.qicaisheng.bookstore.book.infrastructure;

import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.book.domain.BookRepository;
import com.qicaisheng.bookstore.common.Page;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Book> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var bookPage = bookJPARepository.findAll(pageable);
        List<Book> content = bookPage.stream().map(BookConverter::toEntity).toList();
        return new Page<>(content, bookPage.getNumber(), bookPage.getSize(),
                bookPage.getTotalElements(), bookPage.getTotalPages());
    }

    @Override
    public Book update(Book book) {
        BookPO bookPO = BookConverter.toPO(book);
        bookJPARepository.save(bookPO);
        return book;
    }

    @Override
    public List<Book> findAllByIds(List<String> bookIds) {
        List<BookPO> bookPOs = bookJPARepository.findAllById(bookIds);
        return bookPOs.stream().map(BookConverter::toEntity).toList();
    }
}
