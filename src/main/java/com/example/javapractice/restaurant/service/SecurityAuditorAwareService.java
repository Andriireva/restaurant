package com.example.javapractice.restaurant.service;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

// It will return authenticated username
@Service
public class SecurityAuditorAwareService implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(securityContext -> securityContext.getAuthentication())
                .map(authentication -> authentication.getPrincipal())
                .filter(principal -> principal instanceof UserDetails)
                .map(principal -> (UserDetails)principal)
                .map(userDetails -> userDetails.getUsername());
    }
}
