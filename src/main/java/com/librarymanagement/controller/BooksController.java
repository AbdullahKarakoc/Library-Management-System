package com.librarymanagement.controller;

import com.librarymanagement.dto.BooksDto;
import com.librarymanagement.model.BooksModel;
import com.librarymanagement.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RequestMapping("/api")
@RestController
public class BooksController {
    @Autowired
    private BooksService service;


    @PostMapping("/books")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BooksDto addBooks(@RequestBody BooksDto booksDto){
        return service.saveBook(booksDto);
    }


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
    @PreAuthorize("hasAuthority('ADMIN')")
    public BooksDto updateBook(@PathVariable int id, @RequestBody BooksDto booksDto){
        return service.updateBook(id, booksDto);
    }


    @DeleteMapping("/books/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable int id){
        return service.deleteBook(id);
    }





}
