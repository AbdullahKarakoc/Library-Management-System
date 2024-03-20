package com.librarymanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.librarymanagement.domain.model.BooksModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class PublishersModel    {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "publishers",cascade = CascadeType.ALL)
    private List<BooksModel> books;
}
