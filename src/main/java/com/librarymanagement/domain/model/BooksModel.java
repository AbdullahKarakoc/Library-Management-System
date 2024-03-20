package com.librarymanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BooksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Integer release;
    private String type;


    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "authors_id")
    private AuthorsModel authors;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "publishers_id")
    private PublishersModel publishers;

}
