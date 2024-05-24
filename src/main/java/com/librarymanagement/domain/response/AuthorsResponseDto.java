package com.librarymanagement.domain.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class AuthorsResponseDto {

    private UUID id;
    private String name;
    private String surname;
    private Date birthdate;
    private LocalDateTime createDate;
    private LocalDateTime lastModified;
    private String  createBy;
    private String lastModifiedBy;
}
