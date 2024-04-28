package com.librarymanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.librarymanagement.enums.BookCategory;
import com.librarymanagement.enums.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class BooksModel {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String name;
    private Date release;
    private BookCategory bookCategory;
    private BookStatus bookStatus;
    private boolean deleted = Boolean.FALSE;




    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "authors_id")
    private AuthorsModel authors;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "publishers_id")
    private PublishersModel publishers;

}
