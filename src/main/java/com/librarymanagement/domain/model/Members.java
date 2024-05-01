package com.librarymanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.librarymanagement.enums.MemberRole;
import com.librarymanagement.enums.MemberStatus;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")
@SQLDelete(sql = "UPDATE members SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Members {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
    private MemberRole roles;
    private MemberStatus memberStatus;
    private boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver" )
    private List<Books> bookList = new ArrayList<>();


}
