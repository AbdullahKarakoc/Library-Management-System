package com.librarymanagement.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        List<OurUserRole> postRoles = [];
//        List<OurUserRole> updateRoles = [];
//        List<OurUserRole> readRoles = [];
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/","/swagger-ui/index.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/books").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/books").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/booksById/{id}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/booksByName/{name}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/books/{id}").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/books/{id}").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users/me").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/{id}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasAnyAuthority("ADMIN", "USER")

                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new OurUserInfoUserDetailsService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
