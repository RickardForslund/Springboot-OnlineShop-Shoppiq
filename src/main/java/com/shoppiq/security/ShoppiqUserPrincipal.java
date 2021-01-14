package com.shoppiq.security;

import com.shoppiq.entity.AuthGroup;
import com.shoppiq.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

public class ShoppiqUserPrincipal implements UserDetails {

    private User user;
    private List<AuthGroup> authGroups;

    public ShoppiqUserPrincipal(User user, List<AuthGroup> authGroups) {
        super();
        this.user = user;
        this.authGroups = authGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authGroups==null) {
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        authGroups.forEach(group->{
            grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
        });

        return grantedAuthorities;
    }


    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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

