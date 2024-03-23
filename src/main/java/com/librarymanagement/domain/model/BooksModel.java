package com.librarymanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class BooksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Integer release;
    private String type;
    private boolean deleted = Boolean.FALSE;
    private String status;




    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "authors_id")
    private AuthorsModel authors;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "publishers_id")
    private PublishersModel publishers;

}
