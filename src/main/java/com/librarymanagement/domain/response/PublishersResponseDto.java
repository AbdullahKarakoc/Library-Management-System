package com.librarymanagement.domain.response;

import lombok.Data;

import java.util.UUID;

@Data
public class PublishersResponseDto {

    private UUID id;
    private String name;
    private String country;

}
