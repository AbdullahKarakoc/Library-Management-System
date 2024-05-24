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
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/V1/books/","/swagger-ui/index.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/V1/books").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/V1/books").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/V1/books/{id}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/V1/books/search").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/V1/books/{id}").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/V1/books/{userId}/issue/{bookId}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/V1/books/{userId}/return/{bookId}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/V1/books/{id}").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/V1/members").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/V1/members").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/V1/members/me").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/V1/members/{id}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/V1/members/{id}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/V1/authors").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/V1/authors").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/V1/authors/{id}").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/V1/authors/{id}").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/V1/publishers").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/V1/publishers").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/V1/publishers/{id}").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/V1/publishers/{id}").hasAnyAuthority("ADMIN")

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
