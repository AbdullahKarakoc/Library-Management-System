package com.librarymanagement.repository;

import com.librarymanagement.domain.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Members, UUID> {
    Optional<Members> findByEmail(String email);
    Optional<Members> findById(UUID id);
    boolean existsByEmail(String email);

}
