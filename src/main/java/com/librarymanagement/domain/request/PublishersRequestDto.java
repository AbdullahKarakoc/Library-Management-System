package com.librarymanagement.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PublishersRequestDto {

    @NotBlank(message = "publishers name is required")
    private String name;
}
