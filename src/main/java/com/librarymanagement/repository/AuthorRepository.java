package com.librarymanagement.repository;

import com.librarymanagement.domain.model.AuthorsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorsModel, UUID> {

}
