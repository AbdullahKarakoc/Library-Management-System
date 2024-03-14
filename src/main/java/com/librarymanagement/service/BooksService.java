package com.librarymanagement.service;


import com.librarymanagement.dto.BooksDto;
import com.librarymanagement.model.AuthorsModel;
import com.librarymanagement.model.BooksModel;
import com.librarymanagement.model.PublishersModel;
import com.librarymanagement.repository.AuthorRepository;
import com.librarymanagement.repository.BooksRepository;
import com.librarymanagement.repository.PublisherRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class BooksService {


    private final BooksRepository repository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;


//    public List<BooksDto> saveBooks(List<BooksDto> booksDtoList) {
//        return booksDtoList.stream()
//                .map(this::saveBook)
//                .collect(Collectors.toList());
//    }
//
//    public BooksDto saveBooks(BooksDto booksDto) {
//        BooksModel booksModelList = modelMapper.map(booksDto, BooksModel.class);
//        BooksModel savedBooks = repository.saveAndFlush(booksModelList);
//        return modelMapper.map(savedBooks, BooksDto.class);
//    }

    public BooksDto saveBook(BooksDto bookDto) {
        AuthorsModel author = authorRepository.save(modelMapper.map(bookDto.getAuthors(), AuthorsModel.class));
        PublishersModel publisher = publisherRepository.save(modelMapper.map(bookDto.getPublishers(), PublishersModel.class));

        BooksModel book = modelMapper.map(bookDto, BooksModel.class);
        book.setAuthors(author);
        book.setPublishers(publisher);

        BooksModel savedBook = repository.save(book);
        return modelMapper.map(savedBook, BooksDto.class);
    }


    public List<BooksDto> getBooks() {
        List<BooksModel> books = repository.findAll();
        List<BooksDto> dtos = books.stream().map(BooksModel-> modelMapper.map(BooksModel, BooksDto.class)).collect(Collectors.toList());
        return dtos;
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
        existingBooksModel.setRelease(booksModel.getRelease());
        existingBooksModel.setType(booksModel.getType());
        return repository.save(existingBooksModel);
    }

    public String deleteBook(int id) {
        repository.deleteById(id);
        return " !!! Kitap Bilgileri Silindi || silinen id: " +id;
    }


}
