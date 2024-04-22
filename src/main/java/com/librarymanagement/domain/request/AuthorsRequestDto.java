package com.librarymanagement.domain.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AuthorsRequestDto {

    @NotBlank(message = "authors name is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "Author's name must be only characters") // sadece harf olabilir, sayı ve özel karakter olamaz
    @Size(min = 5, max = 30, message = "Author's name length must be between 5 and 20 characters")
    private String name;

    @NotNull(message = "authors age is required")
    @Min(value = 3, message = "Author's age must be at least 5")
    @Max(value = 150, message = "Author's age cannot be greater than 150")
    private Integer age;

    @NotBlank(message = "authors country is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "Country must be only characters") // sadece harf olabilir, sayı ve özel karakter olamaz
    @Size(min = 2, max = 40, message = "Country's name length must be between 2 and 40 characters")
    private String country;

}
