package com.librarymanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.librarymanagement.domain.model.BooksModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class PublishersModel    {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String name;
    private String address;

    @JsonBackReference
    @OneToMany(mappedBy = "publishers",cascade = CascadeType.ALL)
    private List<BooksModel> books;
}
