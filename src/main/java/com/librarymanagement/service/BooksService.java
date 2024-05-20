package com.librarymanagement.service;


import com.librarymanagement.domain.request.BooksRequestDto;
import com.librarymanagement.domain.response.BooksResponseDto;
import com.librarymanagement.enums.BookCategory;
import com.librarymanagement.enums.BookStatus;
import com.librarymanagement.enums.MemberStatus;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Data
public class BooksService {

    @Autowired
    private BooksRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;


    public void saveBook(BooksRequestDto bookDto) {
        Authors author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.AUTHOR_NOT_FOUND.getValue()));

        Publishers publisher = publisherRepository.findById(bookDto.getPublisherId())
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.PUBLISHER_NOT_FOUND.getValue()));

        Books book = modelMapper.map(bookDto, Books.class);
        book.setAuthors(author);
        book.setPublishers(publisher);
        book.setBookStatus(BookStatus.IN_LIBRARY);

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

    public List<BooksResponseDto> getBookByName(String name) {
        List<Books> booksList = bookRepository.findByName(name);

        if (booksList.isEmpty()) {
            throw new DataNotFoundException(ErrorMessages.BOOK_NOT_FOUND.getValue());
        }

        return booksList.stream()
                .map(books -> modelMapper.map(books, BooksResponseDto.class)).toList();
    }


    public BooksResponseDto updateBook(UUID id, BooksRequestDto booksDto) {

        Books existingBook = bookRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.BOOK_NOT_FOUND.getValue()));
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
        if (book.getBookStatus() == BookStatus.BORROWED) {
            throw new UnsupportedOperationException("Cannot delete a book that is borrowed.");
        }

        book.setBookStatus(BookStatus.DELETED);
        bookRepository.save(book);
        bookRepository.deleteById(id);

        return " !!! Book Information Deleted: ";

    }

}