package com.librarymanagement.controller;

import com.librarymanagement.dto.BooksDto;
import com.librarymanagement.model.BooksModel;
import com.librarymanagement.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RequestMapping("/api")
@RestController
@Tag(name = "Books-Controller")
public class BooksController {
    @Autowired
    private BooksService service;


    @Operation(
            description = "Post endpoint for admin",
            summary = "This is a summary for management post endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "403"
                    )
            }
    )

    @PostMapping("/books")
    public BooksDto addBooks(@RequestBody BooksDto booksDto){
        return service.saveBook(booksDto);
    }


    @Operation(
            description = "Get endpoint for all users",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "403"
                    )
            }
    )

    @GetMapping("/books")
    public List<BooksDto> findAllBooks(){
        return service.getBooks();
    }
    @GetMapping("/booksById/{id}")
    public BooksDto findBookById(@PathVariable int id){
        return service.getBookById(id);
    }
    @GetMapping("/booksByName/{name}")
    public BooksDto findBookByName(@PathVariable String name){
        return service.getBookByName(name);
    }


    @PutMapping("/books/{id}")
    public BooksDto updateBook(@PathVariable int id, @RequestBody BooksDto booksDto){
        return service.updateBook(id, booksDto);
    }


    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable int id){
        return service.deleteBook(id);
    }





}
