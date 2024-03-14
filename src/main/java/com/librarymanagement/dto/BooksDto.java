package com.librarymanagement.dto;

import lombok.Data;

@Data
public class BooksDto {

    private String name;
    private Integer release;
    private String type;
    private AuthorsDto authors;
    private PublishersDto publishers;

}
