package com.librarymanagement.service;

import com.librarymanagement.domain.model.BooksModel;
import com.librarymanagement.domain.model.OurUser;
import com.librarymanagement.domain.request.UsersRequestDto;
import com.librarymanagement.domain.response.BooksResponseDto;
import com.librarymanagement.domain.response.UsersResponseDto;
import com.librarymanagement.enums.OurUserStatus;
import com.librarymanagement.exception.UserAlreadyExistsException;
import com.librarymanagement.repository.OurUserRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class UsersService {

    private final OurUserRepo ourUserRepo;
    private final ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final static Logger logger = LoggerFactory.getLogger(UsersService.class);


    public UsersRequestDto saveUser(UsersRequestDto userDto){
        if (ourUserRepo.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists. Please choose a different email address.");
        }

        OurUser user = modelMapper.map(userDto, OurUser.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserStatus(OurUserStatus.ACTIVE);

        OurUser savedUser = ourUserRepo.save(user);
        return modelMapper.map(savedUser, UsersRequestDto.class);

    }

    public List<UsersResponseDto> getUsers() {
        List<OurUser> users = ourUserRepo.findAll();
        List<UsersResponseDto> dtos = users.stream().map(OurUser-> modelMapper.map(OurUser, UsersResponseDto.class)).collect(Collectors.toList());
        return dtos;
    }




}
