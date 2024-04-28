package com.librarymanagement.repository;

import com.librarymanagement.domain.model.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BooksRepository extends JpaRepository<BooksModel, UUID> {
    Optional<BooksModel> findByName(String name);
}
