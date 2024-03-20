package com.librarymanagement.domain.request;

import lombok.Data;

@Data
public class AuthorsRequestDto {

    private String name;
    private Integer age;
    private String country;

}
