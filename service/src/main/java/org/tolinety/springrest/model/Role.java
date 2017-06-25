package org.tolinety.springrest.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by ToliNeTy on 05.03.2017.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
