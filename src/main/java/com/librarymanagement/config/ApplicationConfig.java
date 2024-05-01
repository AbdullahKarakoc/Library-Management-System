package com.librarymanagement.config;

import com.librarymanagement.auditing.ApplicationAuditAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.domain.AuditorAware;

import java.util.UUID;

@Configuration
public class ApplicationConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return new ApplicationAuditAware();
    }

}
