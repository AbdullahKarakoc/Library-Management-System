package com.librarymanagement.service;

import com.librarymanagement.domain.model.Authors;
import com.librarymanagement.domain.request.AuthorsRequestDto;
import com.librarymanagement.domain.response.AuthorsResponseDto;
import com.librarymanagement.exception.DataNotFoundException;
import com.librarymanagement.exception.AuthorAlreadyExistsException;
import com.librarymanagement.repository.AuthorRepository;
import com.librarymanagement.util.ErrorMessages;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class AuthorsService {

    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public AuthorsRequestDto saveAuthor(AuthorsRequestDto authorDto) {
        Authors author = modelMapper.map(authorDto, Authors.class);
        Authors savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, AuthorsRequestDto.class);
    }

    public List<AuthorsResponseDto> getAuthors() {
        List<Authors> authors = authorRepository.findAll();
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorsResponseDto.class))
                .collect(Collectors.toList());
    }

    public AuthorsResponseDto getAuthorById(UUID authorId) {
        Authors author = authorRepository.findById(authorId)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.AUTHOR_NOT_FOUND.getValue()));
        return modelMapper.map(author, AuthorsResponseDto.class);
    }

    public AuthorsRequestDto updateAuthor(UUID authorId, AuthorsRequestDto updatedAuthorDto) {
        Authors existingAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.AUTHOR_NOT_FOUND.getValue()));

        existingAuthor.setName(updatedAuthorDto.getName());
        existingAuthor.setSurname(updatedAuthorDto.getSurname());
        existingAuthor.setBirthdate(updatedAuthorDto.getBirthdate());

        Authors savedAuthor = authorRepository.save(existingAuthor);
        return modelMapper.map(savedAuthor, AuthorsRequestDto.class);
    }

    public void deleteAuthor(UUID authorId) {
        Authors author = authorRepository.findById(authorId)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.AUTHOR_NOT_FOUND.getValue()));
        
        authorRepository.deleteById(authorId);
    }
}
