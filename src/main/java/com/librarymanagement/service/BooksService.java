package com.librarymanagement.service;


import com.librarymanagement.domain.request.BooksRequestDto;
import com.librarymanagement.domain.response.BooksResponseDto;
import com.librarymanagement.enums.BookStatus;
import com.librarymanagement.exception.DataNotFoundException;
import com.librarymanagement.domain.model.Authors;
import com.librarymanagement.domain.model.Books;
import com.librarymanagement.domain.model.Publishers;
import com.librarymanagement.exception.UserAlreadyExistsException;
import com.librarymanagement.repository.AuthorRepository;
import com.librarymanagement.repository.BooksRepository;
import com.librarymanagement.repository.PublisherRepository;
import com.librarymanagement.util.ErrorMessages;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Data
public class BooksService {


    private final BooksRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;




    public void saveBook(BooksRequestDto bookDto) {
        if (bookRepository.existsByName(bookDto.getName())) {
            throw new UserAlreadyExistsException("Book already exists.");
        }


        Authors author = authorRepository.save(modelMapper.map(bookDto.getAuthors(), Authors.class));
        Publishers publisher = publisherRepository.save(modelMapper.map(bookDto.getPublishers(), Publishers.class));

        Books book = modelMapper.map(bookDto, Books.class);
        book.setAuthors(author);
        book.setPublishers(publisher);
        book.setBookStatus(BookStatus.KUTUPHANEDE);

        Books savedBook = bookRepository.save(book);
        modelMapper.map(savedBook, BooksRequestDto.class);
    }


    public List<BooksResponseDto> getBooks() {
        List<Books> books = bookRepository.findAll();
        List<BooksResponseDto> dtos = books.stream().map(BooksModel-> modelMapper.map(BooksModel, BooksResponseDto.class)).toList();
        return dtos;
    }

    public BooksResponseDto getBookById(UUID id) {
        Books books = bookRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.BOOK_NOT_FOUND.getValue()));
       return modelMapper.map(books, BooksResponseDto.class);
    }

    public BooksResponseDto getBookByName(String name) {
        Books books = bookRepository.findByName(name).orElseThrow(() -> new DataNotFoundException(ErrorMessages.BOOK_NOT_FOUND.getValue()));
        return modelMapper.map(books, BooksResponseDto.class);
    }


    public BooksResponseDto updateBook(UUID id, BooksRequestDto booksDto) {
        if (bookRepository.existsByName(booksDto.getName())) {
            throw new UserAlreadyExistsException("Book name already exists.");
        }

        Books optionalBooks = bookRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.BOOK_NOT_FOUND.getValue()));

            Books existingBook = optionalBooks;
            existingBook.setName(booksDto.getName());
            existingBook.setRelease(booksDto.getRelease());
            existingBook.setBookCategory(booksDto.getBookCategory());

            Authors author = authorRepository.save(modelMapper.map(booksDto.getAuthors(), Authors.class));
            existingBook.setAuthors(author);

            Publishers publisher = publisherRepository.save(modelMapper.map(booksDto.getPublishers(), Publishers.class));
            existingBook.setPublishers(publisher);

            Books updatedBook = bookRepository.save(existingBook);
            return modelMapper.map(updatedBook, BooksResponseDto.class);

        }


    public String deleteBook(UUID id) {

        Books book = bookRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.BOOK_NOT_FOUND.getValue()));
        bookRepository.save(book);
        bookRepository.deleteById(id);
        return " !!! Book Information Deleted || deleted id: " + id;

    }

}