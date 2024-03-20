package com.librarymanagement.repository;

import com.librarymanagement.domain.model.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksRepository extends JpaRepository<BooksModel, Integer> {
    Optional<BooksModel> findByName(String name);
}
