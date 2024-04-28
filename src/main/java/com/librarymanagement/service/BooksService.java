package com.librarymanagement.service;


import com.librarymanagement.domain.request.BooksRequestDto;
import com.librarymanagement.domain.response.BooksResponseDto;
import com.librarymanagement.enums.BookStatus;
import com.librarymanagement.exception.DataNotFoundException;
import com.librarymanagement.domain.model.AuthorsModel;
import com.librarymanagement.domain.model.BooksModel;
import com.librarymanagement.domain.model.PublishersModel;
import com.librarymanagement.repository.AuthorRepository;
import com.librarymanagement.repository.BooksRepository;
import com.librarymanagement.repository.PublisherRepository;
import com.librarymanagement.util.ErrorMessages;
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




    public BooksRequestDto saveBook(BooksRequestDto bookDto) {
        AuthorsModel author = authorRepository.save(modelMapper.map(bookDto.getAuthors(), AuthorsModel.class));
        PublishersModel publisher = publisherRepository.save(modelMapper.map(bookDto.getPublishers(), PublishersModel.class));

        BooksModel book = modelMapper.map(bookDto, BooksModel.class);
        book.setAuthors(author);
        book.setPublishers(publisher);
        book.setBookStatus(BookStatus.KUTUPHANEDE);

        BooksModel savedBook = repository.save(book);
        return modelMapper.map(savedBook, BooksRequestDto.class);
    }


    public List<BooksResponseDto> getBooks() {
        List<BooksModel> books = repository.findAll();
        List<BooksResponseDto> dtos = books.stream().map(BooksModel-> modelMapper.map(BooksModel, BooksResponseDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public BooksResponseDto getBookById(int id) {
        BooksModel booksModel = repository.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
       return modelMapper.map(booksModel, BooksResponseDto.class);
    }

    public BooksResponseDto getBookByName(String name) {
        BooksModel booksModel = repository.findByName(name).orElseThrow(() -> new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        return modelMapper.map(booksModel, BooksResponseDto.class);
    }


    public BooksResponseDto updateBook(int id, BooksRequestDto booksDto) {
        BooksModel optionalBooksModel = repository.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));

            BooksModel existingBook = optionalBooksModel;
            existingBook.setName(booksDto.getName());
            existingBook.setRelease(booksDto.getRelease());
            existingBook.setBookCategory(booksDto.getBookCategory());

            AuthorsModel author = authorRepository.save(modelMapper.map(booksDto.getAuthors(), AuthorsModel.class));
            existingBook.setAuthors(author);

            PublishersModel publisher = publisherRepository.save(modelMapper.map(booksDto.getPublishers(), PublishersModel.class));
            existingBook.setPublishers(publisher);

            BooksModel updatedBook = repository.save(existingBook);
            return modelMapper.map(updatedBook, BooksResponseDto.class);

        }


    public String deleteBook(int id) {

        BooksModel book = repository.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        repository.save(book);
        repository.deleteById(id);
        return " !!! Kitap Bilgileri Silindi || silinen id: " + id;

    }

}