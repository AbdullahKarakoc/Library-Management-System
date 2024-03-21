package com.librarymanagement.controller;

import com.librarymanagement.component.SwaggerAnnotations;
import com.librarymanagement.domain.model.OurUser;
import com.librarymanagement.repository.OurUserRepo;
import com.librarymanagement.repository.BooksRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Users-Controller", description = "Controller managing operations related to users")
public class UsersController {
    @Autowired
    private OurUserRepo ourUserRepo;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SwaggerAnnotations swaggerAnnotations;


    @Operation(
            summary = "Go to the homepage",
            description = "An endpoint to go to the general homepage without requiring authentication."
    )
    @GetMapping("/")
    public String goHome() {
        return "This is publickly accesible withing needing authentication ";
    }


    @Operation(
            summary = "Save a new user",
            description = "An endpoint used to save a new user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully saved"),
                    @ApiResponse(responseCode = "404", description = "Error, user not saved")
            }
    )
    @PostMapping("/users")
    public ResponseEntity<Object> saveUSer(@RequestBody OurUser ourUser) {
        ourUser.setPassword(passwordEncoder.encode(ourUser.getPassword()));
        OurUser result = ourUserRepo.save(ourUser);
        if (result.getId() > 0) {
            return ResponseEntity.ok("User Was Saved");
        }
        return ResponseEntity.status(404).body("Error, User Not Saved");
    }


    @Operation(
            summary = "Get all users",
            description = "An endpoint used to list all users."
    )
    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(ourUserRepo.findAll());
    }


    @Operation(
            summary = "Get my details",
            description = "An endpoint used to get details of the logged-in user."
    )
    @GetMapping("/users/single")
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
