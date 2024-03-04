package com.librarymanagement.controller;

import com.librarymanagement.model.BooksModel;
import com.librarymanagement.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RequestMapping("/api")
@RestController
public class BooksController {
    @Autowired
    private BooksService service;

    @PostMapping("/addBook")
    public BooksModel addBook(@RequestBody BooksModel booksModel){
        return service.saveBook(booksModel);
    }
    @PostMapping("/addBooks")
    public List<BooksModel> addBooks(@RequestBody List<BooksModel> booksModels){
        return service.saveBooks(booksModels);
    }


    @GetMapping("/books")
    public List<BooksModel> findAllBooks(){
        return service.getBooks();
    }
    @GetMapping("/bookById/{id}")
    public BooksModel findBookById(@PathVariable int id){
        return service.getBookById(id);
    }
    @GetMapping("/book/{name}")
    public BooksModel findBookByName(@PathVariable String name){
        return service.getBookByName(name);
    }


    @PutMapping("/update")
    public BooksModel updateBook(@RequestBody BooksModel booksModel){
        return service.updateBook(booksModel);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id){
        return service.deleteBook(id);
    }





}
