package com.librarymanagement.domain.response;

import lombok.Data;

@Data
public class BooksResponseDto {

    private String name;
    private Integer release;
    private String type;
    private AuthorsResponseDto authors;
    private PublishersResponseDto publishers;

}
