package ru.javaops.topjava.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_REGULAR_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}