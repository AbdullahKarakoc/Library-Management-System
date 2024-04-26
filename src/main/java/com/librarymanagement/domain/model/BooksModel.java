package com.librarymanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.librarymanagement.enums.BookCategory;
import com.librarymanagement.enums.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

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
    private Date release;
    private BookCategory bookCategory;
    private boolean deleted = Boolean.FALSE;
    private BookStatus bookStatus;



    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "authors_id")
    private AuthorsModel authors;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "publishers_id")
    private PublishersModel publishers;

}
