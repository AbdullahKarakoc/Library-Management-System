package com.librarymanagement.repository;

import com.librarymanagement.domain.model.PublishersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublishersModel, Integer> {
}
