package com.librarymanagement.controller;

import com.librarymanagement.model.OurUser;
import com.librarymanagement.repository.OurUserRepo;
import com.librarymanagement.repository.BooksRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Users-Controller")
public class UsersController {
    @Autowired
    private OurUserRepo ourUserRepo;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String goHome() {
        return "This is publickly accesible withing needing authentication ";
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUSer(@RequestBody OurUser ourUser) {
        ourUser.setPassword(passwordEncoder.encode(ourUser.getPassword()));
        OurUser result = ourUserRepo.save(ourUser);
        if (result.getId() > 0) {
            return ResponseEntity.ok("User Was Saved");
        }
        return ResponseEntity.status(404).body("Error, User Not Saved");
    }
    
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(ourUserRepo.findAll());
    }

    @GetMapping("/users/single")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getMyDetails() {
        return ResponseEntity.ok(ourUserRepo.findByEmail(getLoggedInUserDetails().getUsername()));
    }

    public UserDetails getLoggedInUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
