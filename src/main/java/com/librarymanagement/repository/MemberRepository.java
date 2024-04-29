package com.librarymanagement.repository;

import com.librarymanagement.domain.model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<MemberModel, UUID> {
    @Query(value = "select * from members where email = ?1", nativeQuery = true)
    Optional<MemberModel> findByEmail(String email);
    Optional<MemberModel> findById(UUID id);
    boolean existsByEmail(String email);

}
