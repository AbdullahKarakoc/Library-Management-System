package com.librarymanagement.service;

import com.librarymanagement.model.UsersModel;
import com.librarymanagement.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;
    public UsersDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel usersModel = usersRepository.findByUsername(username);
        if(usersModel == null){
            throw new UsernameNotFoundException("User not found");
        }
        return usersModel;
    }
}
