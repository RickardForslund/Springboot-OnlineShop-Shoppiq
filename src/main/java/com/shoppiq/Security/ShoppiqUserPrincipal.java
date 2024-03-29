package com.shoppiq.Security;

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
        if (authGroups == null) {
            return Collections.emptyList();
        }
            Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
            authGroups.forEach(authGroup -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(authGroup.getAuthGroup()));
            });
        return grantedAuthorities;
    }

    public User getUser() {
        return user;
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
