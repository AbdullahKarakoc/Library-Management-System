package com.librarymanagement.auditing;

import com.librarymanagement.config.security.OurUserInfoDetails;
import com.librarymanagement.domain.model.Members;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class ApplicationAuditAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }

        OurUserInfoDetails userPrincipal = (OurUserInfoDetails) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getUsername());
    }
}