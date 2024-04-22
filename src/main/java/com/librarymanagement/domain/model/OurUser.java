package com.librarymanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
@Table(name = "ourusers")
public class OurUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @Email(message = "Mail is not valid")
    private String email;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "Password is not valid") // 1 büyük, 1 küçük, 1 özel karakter ve 8-20 arası boyut
    private String password;

    @NotBlank(message = "role is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$",message = "User roles not valid") // sadece harf olabilir, sayı ve özel karakter olamaz
    private String roles;
}
