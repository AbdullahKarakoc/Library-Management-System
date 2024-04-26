package com.librarymanagement.domain.response;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorsResponseDto {

    private String name;
    private String surname;
    private Date birthdate;

}
