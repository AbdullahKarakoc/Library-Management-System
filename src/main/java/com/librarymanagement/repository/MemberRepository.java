package com.librarymanagement.repository;

import com.librarymanagement.domain.model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<MemberModel, UUID> {
    @Query(value = "select * from ourusers where email = ?1", nativeQuery = true)
    Optional<MemberModel> findByEmail(String email);
    boolean existsByEmail(String email);

}
