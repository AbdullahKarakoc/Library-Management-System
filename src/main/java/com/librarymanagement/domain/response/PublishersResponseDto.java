package com.librarymanagement.domain.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PublishersResponseDto {

    private UUID id;
    private String name;
    private String country;
    private LocalDateTime createDate;
    private LocalDateTime lastModified;
    private String  createBy;
    private String lastModifiedBy;
}
