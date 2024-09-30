package com.qicaisheng.bookstore.book;

import com.qicaisheng.bookstore.book.dto.BookCreateRequestDTO;
import com.qicaisheng.bookstore.book.dto.BookUpdateRequestDTO;
import com.qicaisheng.bookstore.book.domain.Book;
import com.qicaisheng.bookstore.common.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books/")
@RequiredArgsConstructor
@Tag(name = "Books Management", description = "Operations related to books management")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Retrieve a paginated list of books",
            description = "Fetches a list of books with pagination support.")
    @GetMapping
    public Page<Book> list(
            @Parameter(description = "Page number to retrieve", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        return bookService.list(page, size);
    }

    @Operation(summary = "Create a new book",
            description = "Creates a new book using the provided book data.")
    @PostMapping
    public Book create(
            @Valid @RequestBody BookCreateRequestDTO bookCreateRequestDTO) {
        return bookService.save(bookCreateRequestDTO);
    }

    @Operation(summary = "Update an existing book",
            description = "Updates the book with the specified ID using the provided data.")
    @PutMapping("/{id}")
    public Book update(
            @Parameter(description = "ID of the book to be updated", example = "1")
            @PathVariable String id,
            @Valid @RequestBody BookUpdateRequestDTO book) {
        return bookService.update(id, book);
    }
}
