package com.librarymanagement.repository;

import com.librarymanagement.domain.model.OurUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OurUserRepo extends JpaRepository<OurUser, UUID> {
    @Query(value = "select * from ourusers where email = ?1", nativeQuery = true)
    Optional<OurUser> findByEmail(String email);
    boolean existsByEmail(String email);

}
