package com.librarymanagement.config.security;

import com.librarymanagement.domain.model.MemberModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OurUserInfoDetails implements UserDetails {
    private String email;
    private String password;
    private List<GrantedAuthority> roles;

    public OurUserInfoDetails(MemberModel memberModel){
        this.email = memberModel.getEmail();
        this.password = memberModel.getPassword();
        this.roles = Stream.of(memberModel.getRoles())
                .map(Enum::name) // Assuming getRoles() returns an array of enums
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}