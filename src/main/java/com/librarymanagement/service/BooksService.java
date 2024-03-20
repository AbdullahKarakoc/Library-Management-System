package com.librarymanagement.service;


import com.librarymanagement.dto.BooksDto;
import com.librarymanagement.exception.DataNotFoundException;
import com.librarymanagement.exception.InternalServerException;
import com.librarymanagement.model.AuthorsModel;
import com.librarymanagement.model.BooksModel;
import com.librarymanagement.model.PublishersModel;
import com.librarymanagement.repository.AuthorRepository;
import com.librarymanagement.repository.BooksRepository;
import com.librarymanagement.repository.PublisherRepository;
import com.librarymanagement.util.ErrorMessages;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class BooksService {


    private final BooksRepository repository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;



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

    public BooksDto getBookById(int id) {
        BooksModel booksModel = repository.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        return modelMapper.map(booksModel, BooksDto.class);
    }

    public BooksDto getBookByName(String name) {
        BooksModel booksModel = repository.findByName(name).orElseThrow(() -> new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        return modelMapper.map(booksModel, BooksDto.class);
    }

    public BooksDto updateBook(int id, BooksDto booksDto) {
        BooksModel optionalBooksModel = repository.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));

            BooksModel existingBook = optionalBooksModel;
            existingBook.setName(booksDto.getName());
            existingBook.setRelease(booksDto.getRelease());
            existingBook.setType(booksDto.getType());

            AuthorsModel author = authorRepository.save(modelMapper.map(booksDto.getAuthors(), AuthorsModel.class));
            existingBook.setAuthors(author);

            PublishersModel publisher = publisherRepository.save(modelMapper.map(booksDto.getPublishers(), PublishersModel.class));
            existingBook.setPublishers(publisher);

            BooksModel updatedBook = repository.save(existingBook);
            return modelMapper.map(updatedBook, BooksDto.class);

        }


    public String deleteBook(int id) {

        repository.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        repository.deleteById(id);
        return " !!! Kitap Bilgileri Silindi || silinen id: " + id;

    }

}