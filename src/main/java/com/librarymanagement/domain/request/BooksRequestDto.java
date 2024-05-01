package com.librarymanagement.domain.request;

import com.librarymanagement.enums.BookCategory;
import com.librarymanagement.util.validator.ValidCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BooksRequestDto {

    @NotBlank(message = "book name is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "Book's name must be only character") // sadece harf olabilir, sayı ve özel karakter olamaz
    @Size(min = 1, max = 50, message = "Book's name length must be between 1 and 50 characters")
    private String name;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past(message = "Birthdate must be in the past")
    private Date release;


    //@NotNull(message = "book bookCategory is required")
    //@ValidCategory(message = "BookCategory is not valid")
    private BookCategory bookCategory;


    @Valid
    private  AuthorsRequestDto authors;
    @Valid
    private  PublishersRequestDto publishers;

    
}
