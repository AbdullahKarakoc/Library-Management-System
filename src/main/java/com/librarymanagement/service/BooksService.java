package com.librarymanagement.service;


import com.librarymanagement.model.BooksModel;
import com.librarymanagement.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    private BooksRepository repository;

    public BooksService(BooksRepository repository){
        this.repository = repository;
    }


    public BooksModel saveBook(BooksModel booksModel) {
        return repository.save(booksModel);
    }

    public List<BooksModel> saveBooks(List<BooksModel> booksModels) {
        return repository.saveAll(booksModels);
    }

    public List<BooksModel> getBooks() {
        return repository.findAll();
    }

    public BooksModel getBookById(int id) {
        return repository.findById(id).orElse(null);
    }

    public BooksModel getBookByName(String name) {
        return repository.findByName(name);
    }

    public BooksModel updateBook(BooksModel booksModel) {
        BooksModel existingBooksModel = repository.findById(booksModel.getId()).orElse(null);
        assert existingBooksModel != null;
        existingBooksModel.setName(booksModel.getName());
        existingBooksModel.setAuthor(booksModel.getAuthor());
        existingBooksModel.setRelease(booksModel.getRelease());
        existingBooksModel.setType(booksModel.getType());
        return repository.save(existingBooksModel);
    }

    public String deleteBook(int id) {
        repository.deleteById(id);
        return " !!! Kitap Bilgileri Silindi || silinen id: " +id;
    }


}
