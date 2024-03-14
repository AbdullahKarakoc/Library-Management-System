package com.librarymanagement.repository;

import com.librarymanagement.model.AuthorsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorsModel, Integer> {

}
