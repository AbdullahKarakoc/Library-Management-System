package com.librarymanagement.domain.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BooksRequestDto {

    @NotBlank(message = "book name is required")
    @Size(min = 1, max = 50, message = "Book's name length must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "book release is required")
    @Size(max = 2024, message = "Future date cannot be entered")
    private Integer release;

    @NotBlank(message = "book type is required")
    @Size(min = 5, max = 20, message = "Book's type length must be between 5 and 20 characters")
    private String type;

    @Valid
    private  AuthorsRequestDto authors;
    @Valid
    private  PublishersRequestDto publishers;

    
}
