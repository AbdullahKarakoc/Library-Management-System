package com.librarymanagement.domain.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AuthorsRequestDto {

    @NotBlank(message = "authors name is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "Author's name must be only characters")
    @Size(min = 5, max = 30, message = "Author's name length must be between 5 and 20 characters")
    private String name;

    @NotBlank(message = "authors name is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "Author's surname must be only characters")
    @Size(min = 5, max = 30, message = "Author's surname length must be between 5 and 20 characters")
    private String surname;


    @NotNull(message = "author's birthdate is required")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past(message = "Birthdate must be in the past")
    private Date birthdate;

}
