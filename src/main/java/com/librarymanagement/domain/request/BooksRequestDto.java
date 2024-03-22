package com.librarymanagement.domain.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BooksRequestDto {

    @NotBlank(message = "book name is required")
    @Size(min = 2, max = 50, message = "publisher's name length must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "book release is required")
    private Integer release;

    @NotBlank(message = "book type is required")
    private String type;

    @Valid
    private  AuthorsRequestDto authors;
    @Valid
    private  PublishersRequestDto publishers;

}
