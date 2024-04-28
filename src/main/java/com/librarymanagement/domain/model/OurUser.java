package com.librarymanagement.domain.model;

import com.librarymanagement.enums.OurUserRole;
import com.librarymanagement.enums.OurUserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ourusers")
@SQLDelete(sql = "UPDATE ourusers SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class OurUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
    private OurUserRole roles;
    private OurUserStatus userStatus;
    private boolean deleted = Boolean.FALSE;

}
