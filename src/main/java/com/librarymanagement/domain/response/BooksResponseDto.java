package com.librarymanagement.domain.response;

import com.librarymanagement.enums.BookCategory;
import lombok.Data;

import java.util.Date;

@Data
public class BooksResponseDto {

    private String name;
    private Date release;
    private BookCategory bookCategory;
    private AuthorsResponseDto authors;
    private PublishersResponseDto publishers;

}
