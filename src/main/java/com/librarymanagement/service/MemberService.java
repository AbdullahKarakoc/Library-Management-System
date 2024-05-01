package com.librarymanagement.service;

import com.librarymanagement.domain.model.Members;
import com.librarymanagement.domain.request.MemberRequestDto;
import com.librarymanagement.domain.response.MemberResponseDto;
import com.librarymanagement.enums.MemberStatus;
import com.librarymanagement.exception.DataNotFoundException;
import com.librarymanagement.exception.UserAlreadyExistsException;
import com.librarymanagement.repository.MemberRepository;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final static Logger logger = LoggerFactory.getLogger(MemberService.class);


    public MemberRequestDto saveUser(MemberRequestDto userDto){
        if (memberRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists. Please choose a different email address.");
        }

        Members user = modelMapper.map(userDto, Members.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserStatus(MemberStatus.ACTIVE);

        Members savedUser = memberRepository.save(user);
        return modelMapper.map(savedUser, MemberRequestDto.class);

    }


    public List<MemberResponseDto> getUsers() {
        List<Members> users = memberRepository.findAll();
        List<MemberResponseDto> dtos = users.stream().map(MemberModel -> modelMapper.map(MemberModel, MemberResponseDto.class)).collect(Collectors.toList());
        return dtos;
    }


    public MemberResponseDto getUser() {
        Optional<Members> user = memberRepository.findByEmail(getLoggedInUserDetails().getUsername());
        MemberResponseDto dto = modelMapper.map(user, MemberResponseDto.class);
        return dto;
    }

    public UserDetails getLoggedInUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }



    public MemberRequestDto updateUser(UUID userId, MemberRequestDto updatedUserDto) {
        Members existingUser = memberRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(ErrorMessages.USER_NOT_FOUND.getValue()));

        existingUser.setName(updatedUserDto.getName());
        existingUser.setSurname(updatedUserDto.getSurname());
        //existingUser.setEmail(updatedUserDto.getEmail()); email bilgileri güncellenemez
        //existingUser.setRoles(updatedUserDto.getRoles()); role bilgileri güncellenemez
        existingUser.setPassword(passwordEncoder.encode(updatedUserDto.getPassword()));
        existingUser.setPhone(updatedUserDto.getPhone());

        Members savedUser = memberRepository.save(existingUser);
        return modelMapper.map(savedUser, MemberRequestDto.class);
    }

    public void deleteUser(UUID userId) {
        Members userToDelete = memberRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(ErrorMessages.USER_NOT_FOUND.getValue()));
        userToDelete.setUserStatus(MemberStatus.INACTIVE);
        memberRepository.save(userToDelete);
        memberRepository.deleteById(userId);


    }
}



