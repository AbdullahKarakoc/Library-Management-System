package com.librarymanagement.repository;

import com.librarymanagement.domain.model.Publishers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Publishers, UUID> {
}
