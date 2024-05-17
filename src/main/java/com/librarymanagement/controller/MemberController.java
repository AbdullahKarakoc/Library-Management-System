package com.librarymanagement.controller;

import com.librarymanagement.component.SwaggerAnnotations;
import com.librarymanagement.domain.request.MemberRequestDto;
import com.librarymanagement.domain.response.MemberResponseDto;
import com.librarymanagement.repository.MemberRepository;
import com.librarymanagement.repository.BooksRepository;
import com.librarymanagement.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Tag(name = "Users-Controller", description = "Controller managing operations related to users")
public class MemberController {
    @Autowired
    private MemberService memberService;


    @Operation(
            summary = "Save a new user",
            description = "An endpoint used to save a new user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully saved"),
                    @ApiResponse(responseCode = "404", description = "Error, user not saved")
            }
    )
    @PostMapping("/users")
    public ResponseEntity<String> addUser(@Valid @RequestBody MemberRequestDto userDto) {
        MemberRequestDto savedUser = memberService.saveUser(userDto);
        return ResponseEntity.ok("User successfully added");
    }


    @Operation(
            summary = "Get all users",
            description = "An endpoint used to list all users."
    )
    @GetMapping("/users")
    public ResponseEntity<List<MemberResponseDto>> getAllUsers() {
        List<MemberResponseDto> allUsers = memberService.getUsers();
        return ResponseEntity.ok(allUsers);

    }


    @Operation(
            summary = "Get my details",
            description = "An endpoint used to get details of the logged-in user."
    )
    @GetMapping("/users/me")
    public ResponseEntity<Object> getMyDetails() {
        MemberResponseDto user = memberService.getUser();
        return ResponseEntity.ok(user);
    }




    @Operation(
            summary = "Update user",
            description = "An endpoint used to update an existing user by their ID."
    )
    @PutMapping("/users/{userId}")
    public ResponseEntity<String>  updateUser(@PathVariable UUID userId, @Valid @RequestBody MemberRequestDto updatedUserDto) {
        MemberRequestDto updatedUser = memberService.updateUser(userId, updatedUserDto);
        return ResponseEntity.ok("User successfully updated");
    }


    @Operation(
            summary = "Delete user",
            description = "An endpoint used to delete an existing user by their ID."
    )
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        memberService.deleteUser(userId);
        return ResponseEntity.ok("User successfully deleted");
    }


}
