package com.librarymanagement.service;

import com.librarymanagement.domain.model.OurUser;
import com.librarymanagement.domain.request.UsersRequestDto;
import com.librarymanagement.domain.response.UsersResponseDto;
import com.librarymanagement.enums.OurUserStatus;
import com.librarymanagement.exception.DataNotFoundException;
import com.librarymanagement.exception.UserAlreadyExistsException;
import com.librarymanagement.repository.OurUserRepo;
import com.librarymanagement.util.ErrorMessages;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


    public UsersResponseDto getUser() {
        Optional<OurUser> user = ourUserRepo.findByEmail(getLoggedInUserDetails().getUsername());
        UsersResponseDto dto = modelMapper.map(user, UsersResponseDto.class);
        return dto;
    }

    public UserDetails getLoggedInUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }



    public UsersRequestDto updateUser(int userId, UsersRequestDto updatedUserDto) {
        OurUser existingUser = ourUserRepo.findById(userId).orElseThrow(() -> new DataNotFoundException(ErrorMessages.USER_NOT_FOUND.getValue()));

        existingUser.setName(updatedUserDto.getName());
        existingUser.setSurname(updatedUserDto.getSurname());
        //existingUser.setEmail(updatedUserDto.getEmail()); email bilgileri güncellenemez
        //existingUser.setRoles(updatedUserDto.getRoles()); role bilgileri güncellenemez
        existingUser.setPassword(passwordEncoder.encode(updatedUserDto.getPassword()));
        existingUser.setPhone(updatedUserDto.getPhone());

        OurUser savedUser = ourUserRepo.save(existingUser);
        return modelMapper.map(savedUser, UsersRequestDto.class);
    }

    public void deleteUser(int userId) {
        OurUser userToDelete = ourUserRepo.findById(userId).orElseThrow(() -> new DataNotFoundException(ErrorMessages.USER_NOT_FOUND.getValue()));
        userToDelete.setUserStatus(OurUserStatus.INACTIVE);
        ourUserRepo.save(userToDelete);
        ourUserRepo.deleteById(userId);


    }
}



