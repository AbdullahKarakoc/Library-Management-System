package com.librarymanagement.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorsRequestDto {

    @NotBlank(message = "authors name is required")
    private String name;

    @NotNull(message = "authors age is required")
    private Integer age;

    @NotBlank(message = "authors country is required")
    private String country;

}
