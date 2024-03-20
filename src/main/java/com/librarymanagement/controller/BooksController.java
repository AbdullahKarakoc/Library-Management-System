package com.librarymanagement.controller;

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
    public BooksRequestDto addBooks(@RequestBody BooksRequestDto booksDto){
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
    public List<BooksResponseDto> findAllBooks(){
        return service.getBooks();
    }
    @GetMapping("/booksById/{id}")
    public BooksResponseDto findBookById(@PathVariable int id){
        return service.getBookById(id);
    }
    @GetMapping("/booksByName/{name}")
    public BooksResponseDto findBookByName(@PathVariable String name){
        return service.getBookByName(name);
    }


    @PutMapping("/books/{id}")
    public BooksResponseDto updateBook(@PathVariable int id, @RequestBody BooksRequestDto booksDto){
        return service.updateBook(id, booksDto);
    }


    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable int id){
        return service.deleteBook(id);
    }





}
