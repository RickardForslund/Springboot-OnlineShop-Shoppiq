package com.shoppiq.Security;

import com.shoppiq.entity.AuthGroup;
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
        var user = userRepository.findByUsername(username);
        List<AuthGroup> authGroups = authGroupRepository.findByUsername(username);
        if (user.isPresent())
            return new ShoppiqUserPrincipal(user.get(), authGroups);
        else
            throw new RuntimeException(); //TODO Replace exception
    }
}
