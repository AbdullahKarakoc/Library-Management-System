package com.librarymanagement.repository;

import com.librarymanagement.model.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<BooksModel, Integer> {
    BooksModel findByName(String name);
}
