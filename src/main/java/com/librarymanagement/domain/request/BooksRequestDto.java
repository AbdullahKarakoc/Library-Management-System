package com.librarymanagement.domain.request;

import lombok.Data;

@Data
public class BooksRequestDto {

    private String name;
    private Integer release;
    private String type;
    private AuthorsRequestDto authors;
    private PublishersRequestDto publishers;

}
