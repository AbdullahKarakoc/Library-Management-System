package com.librarymanagement.repository;

import com.librarymanagement.domain.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BooksRepository extends JpaRepository<Books, UUID> {
    List<Books> findByName(String name);
    Optional<Books> findById(UUID id);
    boolean existsByName(String name);
}
