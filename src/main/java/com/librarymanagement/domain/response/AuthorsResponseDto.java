package com.librarymanagement.domain.response;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class AuthorsResponseDto {

    private UUID id;
    private String name;
    private String surname;
    private Date birthdate;

}
