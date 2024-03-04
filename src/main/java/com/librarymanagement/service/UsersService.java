package com.librarymanagement.service;

import com.librarymanagement.model.UsersModel;
import com.librarymanagement.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public UsersModel createUser(UsersModel usersModel) {
        return usersRepository.save(usersModel);
    }

    public UsersModel getUserById(Integer userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    public List<UsersModel> getAllUsers() {
        return usersRepository.findAll();
    }

    public UsersModel updateUser(Integer userId, UsersModel usersModel) {
        UsersModel existingUser = usersRepository.findById(userId).orElse(null);
        if(existingUser != null){
            existingUser.setUserName(usersModel.getUserName());
            existingUser.setPassword(usersModel.getPassword());
            return usersRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }
}
