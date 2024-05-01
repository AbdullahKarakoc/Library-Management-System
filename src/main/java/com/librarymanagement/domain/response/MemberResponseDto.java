package com.librarymanagement.domain.response;

import com.librarymanagement.enums.MemberRole;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MemberResponseDto {

    private UUID id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private MemberRole roles;
    private LocalDateTime createDate;
    private LocalDateTime lastModified;

}

