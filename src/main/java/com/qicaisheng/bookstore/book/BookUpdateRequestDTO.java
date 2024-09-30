package com.qicaisheng.bookstore.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Data Transfer Object for updating a new book")
public class BookUpdateRequestDTO extends BookRequestDTO {
}
