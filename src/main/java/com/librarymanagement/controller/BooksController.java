package com.librarymanagement.controller;

import com.librarymanagement.component.SwaggerAnnotations;
import com.librarymanagement.domain.request.BooksRequestDto;
import com.librarymanagement.domain.response.BooksResponseDto;
import com.librarymanagement.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api")
@RestController
@Tag(name = "Books-Controller", description = "Controller managing operations related to books")
public class BooksController {
    @Autowired
    private BooksService service;
    @Autowired
    private SwaggerAnnotations swaggerAnnotations;

    @Operation(
            summary = "Adds a new book",
            description = "An endpoint used by administrators to add a new book.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully added"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PostMapping("/books")
    public ResponseEntity<String> addBooks(@Valid @RequestBody BooksRequestDto booksDto){
        BooksRequestDto savedBook = service.saveBook(booksDto);
        return ResponseEntity.ok("Kitap başarıyla eklendi");
    }


    @Operation(
            summary = "Lists all books",
            description = "An endpoint used to list all books.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping("/books")
    public ResponseEntity<List<BooksResponseDto>> findAllBooks(){
        List<BooksResponseDto> allBooks = service.getBooks();
        return ResponseEntity.ok(allBooks);
    }


    @Operation(
            summary = "Searches for a book by ID",
            description = "Searches for a specific book by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @GetMapping("/booksById/{id}")
    public ResponseEntity<BooksResponseDto> findBookById(@PathVariable UUID id){
        BooksResponseDto book = service.getBookById(id);
        return ResponseEntity.ok(book);
    }


    @Operation(
            summary = "Searches for a book by name",
            description = "Searches for a specific book by its name.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @GetMapping("/booksByName/{name}")
    public ResponseEntity<BooksResponseDto> findBookByName(@PathVariable String name){
        BooksResponseDto book = service.getBookByName(name);
        return ResponseEntity.ok(book);
    }

/*
    @PutMapping("/books/trade")
    public void updateBook(@RequestBody BooksRequestDto book){
        //TODO -> kitap kiralama, satma, kiralanmış kitabı geri getirme
    }

 */


    @Operation(
            summary = "Updates a book",
            description = "Updates the information of an existing book.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @PutMapping("/books/{id}")
    public ResponseEntity<String> updateBook(@PathVariable UUID id, @RequestBody BooksRequestDto booksDto){
        BooksResponseDto updatedBook = service.updateBook(id, booksDto);
        return ResponseEntity.ok("Kitap başarıyla güncellendi");
    }


    @Operation(
            summary = "Deletes a book",
            description = "Deletes an existing book.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
        String result = service.deleteBook(id);
        return ResponseEntity.ok("Kitap başarıyla silindi");
    }


}
