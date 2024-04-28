package com.librarymanagement.domain.response;

import com.librarymanagement.enums.BookCategory;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class BooksResponseDto {

    private UUID id;
    private String name;
    private Date release;
    private BookCategory bookCategory;
    private AuthorsResponseDto authors;
    private PublishersResponseDto publishers;

}
