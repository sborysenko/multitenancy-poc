package com.example.multitenancypoc.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
//        SecurityContextHolder.getContext().getAuthentication()
        return null;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
