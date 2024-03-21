package com.librarymanagement.controller;

import com.librarymanagement.component.SwaggerAnnotations;
import com.librarymanagement.domain.request.BooksRequestDto;
import com.librarymanagement.domain.response.BooksResponseDto;
import com.librarymanagement.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BooksRequestDto addBooks(@RequestBody BooksRequestDto booksDto){
        return service.saveBook(booksDto);
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
    public List<BooksResponseDto> findAllBooks(){
        return service.getBooks();
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
    public BooksResponseDto findBookById(@PathVariable int id){
        return service.getBookById(id);
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
    public BooksResponseDto findBookByName(@PathVariable String name){
        return service.getBookByName(name);
    }


    @Operation(
            summary = "Updates a book",
            description = "Updates the information of an existing book.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @PutMapping("/books/{id}")
    public BooksResponseDto updateBook(@PathVariable int id, @RequestBody BooksRequestDto booksDto){
        return service.updateBook(id, booksDto);
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
    public String deleteBook(@PathVariable int id){
        return service.deleteBook(id);
    }



}
