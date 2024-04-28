package com.librarymanagement.repository;

import com.librarymanagement.domain.model.PublishersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublishersModel, UUID> {
}
