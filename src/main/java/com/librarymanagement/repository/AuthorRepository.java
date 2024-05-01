package com.librarymanagement.repository;

import com.librarymanagement.domain.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Authors, UUID> {

}
