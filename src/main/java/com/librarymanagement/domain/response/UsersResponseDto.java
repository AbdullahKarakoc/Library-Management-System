package com.librarymanagement.domain.response;

import com.librarymanagement.enums.OurUserRole;

import lombok.Data;

import java.util.UUID;

@Data
public class UsersResponseDto {

    private UUID id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private OurUserRole roles;

}

