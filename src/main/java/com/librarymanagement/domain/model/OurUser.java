package com.librarymanagement.domain.model;

import com.librarymanagement.enums.OurUserRole;
import com.librarymanagement.enums.OurUserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "ourusers")
public class OurUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "User name is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "User's name must be only character")
    @Size(min = 1, max = 50, message = "User's name length must be between 1 and 50 characters")
    private String name;

    @NotBlank(message = "User lastname is required")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$",message = "User's lastname must be only character")
    @Size(min = 1, max = 50, message = "User's lastname length must be between 1 and 50 characters")
    private String surname;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^(\\+90|0)?[0-9]{10}$", message = "Phone number is not valid. Please enter a valid Turkish phone number.")
    private String phone;

    @Column(unique = true)
    @Email(message = "Mail is not valid")
    private String email;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "Password is not valid") // 1 büyük, 1 küçük, 1 özel karakter ve min 8 karakter
    private String password;

    private OurUserRole roles;

    private OurUserStatus userStatus;
}
