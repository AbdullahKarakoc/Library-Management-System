package com.librarymanagement.repository;

import com.librarymanagement.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {
    UsersModel findByUserName(String userName);
}
