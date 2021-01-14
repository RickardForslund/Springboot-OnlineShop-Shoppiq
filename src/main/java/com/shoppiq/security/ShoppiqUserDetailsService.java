package com.shoppiq.security;

import com.shoppiq.entity.AuthGroup;
import com.shoppiq.entity.User;
import com.shoppiq.repository.AuthGroupRepository;
import com.shoppiq.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ShoppiqUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private AuthGroupRepository authGroupRepository;

    public ShoppiqUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        super();
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        if(user==null) {
            throw new UsernameNotFoundException("Can't find username: " + username);
        }
        List<AuthGroup> authGroups = authGroupRepository.findByUsername(username);
        return new ShoppiqUserPrincipal(user, authGroups);
    }

}
