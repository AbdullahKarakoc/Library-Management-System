package com.librarymanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class AuthorsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @JsonBackReference
    @OneToMany(mappedBy = "authors",cascade = CascadeType.ALL)
    private List<BooksModel> books;

}
