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
import java.util.UUID;

@Data
public class BooksRequestDto {

    @NotBlank(message = "book name is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "Book's name must be only character")
    @Size(min = 1, max = 50, message = "Book's name length must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "author's birthdate is required")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past(message = "Birthdate must be in the past")
    private Date release;


    //@ValidCategory(message = "BookCategory is not valid")
    @NotNull(message = "book category is required")
    private BookCategory bookCategory;

    @NotNull(message = "Author ID is required")
    private UUID authorId;

    @NotNull(message = "Publisher ID is required")
    private UUID publisherId;
    
}
