package com.librarymanagement.domain.request;

import com.librarymanagement.domain.enums.Category;
import com.librarymanagement.util.IntegerValue;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class BooksRequestDto {

    @NotBlank(message = "book name is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "Book's name must be only character") // sadece harf olabilir, sayı ve özel karakter olamaz
    @Size(min = 1, max = 50, message = "Book's name length must be between 1 and 50 characters")
    private String name;

  /*  @NotNull(message = "book release is required")
    //@Pattern(regexp = "^[0-9]+$",message = "Release must be only number") // sadece sayı
    //@Digits(integer = 4, fraction = 0, message = "Release must contain only numbers")
    @Min(value = 1, message = "Book's release must be at least 1")
    @Max(value = 2024, message = "Book's release cannot be greater than 2024")
    @IntegerValue(message = "Release must be an integer value")
   */

    @Temporal(TemporalType.DATE)
    private Date release;

    @NotBlank(message = "book type is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "Book type must be only character") // sadece harf olabilir, sayı ve özel karakter olamaz
    @Size(min = 5, max = 20, message = "Book's type length must be between 5 and 20 characters")
    private Category category;

    @Valid
    private  AuthorsRequestDto authors;
    @Valid
    private  PublishersRequestDto publishers;

    
}
