package com.librarymanagement.domain.response;

import com.librarymanagement.enums.OurUserRole;

import lombok.Data;

@Data
public class UsersResponseDto {


    private String name;
    private String surname;
    private String phone;
    private String email;
    private OurUserRole roles;

}

