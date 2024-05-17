package com.librarymanagement.controller;

import com.librarymanagement.domain.request.AuthorsRequestDto;
import com.librarymanagement.domain.response.AuthorsResponseDto;
import com.librarymanagement.service.AuthorsService;
import com.librarymanagement.service.AuthorsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors-Controller", description = "Controller managing operations related to authors")
public class AuthorsController {
    @Autowired
    private AuthorsService authorService;

    @Operation(
            summary = "Save a new author",
            description = "An endpoint used to save a new author.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Author successfully saved"),
                    @ApiResponse(responseCode = "404", description = "Error, author not saved")
            }
    )
    @PostMapping()
    public ResponseEntity<String> addAuthor(@Valid @RequestBody AuthorsRequestDto authorDto) {
        AuthorsRequestDto savedAuthor = authorService.saveAuthor(authorDto);
        return ResponseEntity.ok("Author successfully added");
    }

    @Operation(
            summary = "Get all authors",
            description = "An endpoint used to list all authors."
    )
    @GetMapping()
    public ResponseEntity<List<AuthorsResponseDto>> getAllAuthors() {
        List<AuthorsResponseDto> allAuthors = authorService.getAuthors();
        return ResponseEntity.ok(allAuthors);
    }

    @Operation(
            summary = "Get author details by ID",
            description = "An endpoint used to get details of an author by their ID."
    )
    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorsResponseDto> getAuthorById(@PathVariable UUID authorId) {
        AuthorsResponseDto author = authorService.getAuthorById(authorId);
        return ResponseEntity.ok(author);
    }

    @Operation(
            summary = "Update author",
            description = "An endpoint used to update an existing author by their ID."
    )
    @PutMapping("/{authorId}")
    public ResponseEntity<String> updateAuthor(@PathVariable UUID authorId, @Valid @RequestBody AuthorsRequestDto updatedAuthorDto) {
        AuthorsRequestDto updatedAuthor = authorService.updateAuthor(authorId, updatedAuthorDto);
        return ResponseEntity.ok("Author successfully updated");
    }

    @Operation(
            summary = "Delete author",
            description = "An endpoint used to delete an existing author by their ID."
    )
    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable UUID authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.ok("Author successfully deleted");
    }
}

