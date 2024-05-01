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
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "books")
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Books {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String name;
    private Date release;
    private BookCategory bookCategory;
    private BookStatus bookStatus;
    private boolean deleted = Boolean.FALSE;

    private LocalDate bookIssueDate;
    private LocalDate bookReturnDate;
    private boolean isIssued = false;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "authors_id")
    private Authors authors;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "publishers_id")
    private Publishers publishers;

    @ManyToOne
    private Members receiver;


    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModified;

    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private String createBy;

    @LastModifiedBy
    @Column(insertable = false)
    private String lastModifiedBy;

}
