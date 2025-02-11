package com.librarymanagement.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PublishersRequestDto {

    @NotBlank(message = "publishers name is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "Publisher's name must be only characters")
    @Size(min = 2, max = 30, message = "Publisher's name length must be between 2 and 30 characters")
    private String name;

    @NotBlank(message = "publishers country is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "Publisher's country must be only characters")
    private String country;
}
