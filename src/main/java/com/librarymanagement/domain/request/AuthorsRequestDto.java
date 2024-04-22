package com.librarymanagement.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthorsRequestDto {

    @NotBlank(message = "authors name is required")
    @Size(min = 10, max = 40, message = "Book's name length must be between 10 and 40 characters")
    private String name;

    @NotNull(message = "authors age is required")
    @Size(min = 3, max = 150, message = "Author's age must be between 3 and 150")
    private Integer age;

    @NotBlank(message = "authors country is required")
    @Size(min = 2, max = 40, message = "Country's name length must be between 2 and 40 characters")
    private String country;

}
