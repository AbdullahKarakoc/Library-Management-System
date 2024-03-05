package com.librarymanagement.repository;

import com.librarymanagement.model.UsersModel;

public interface UsersRepository {
    UsersModel findByUsername(String username);
}
