package com.librarymanagement.controller;

import com.librarymanagement.model.UsersModel;
import com.librarymanagement.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<UsersModel> registerUser(@RequestBody UsersModel usersModel){
        UsersModel newUser = usersService.createUser(usersModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UsersModel> getUserById(@PathVariable Integer userId) {
        UsersModel usersModel = usersService.getUserById(userId);
        if (usersModel != null){
            return ResponseEntity.ok(usersModel);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("")
    public ResponseEntity<List<UsersModel>> getAllUsers(){
        List<UsersModel> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UsersModel> updateUser(@PathVariable Integer userId, @RequestBody UsersModel usersModel){
        UsersModel updatedUser = usersService.updateUser(userId, usersModel);
        if (updatedUser != null){
            return ResponseEntity.ok(updatedUser);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId){
        usersService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
