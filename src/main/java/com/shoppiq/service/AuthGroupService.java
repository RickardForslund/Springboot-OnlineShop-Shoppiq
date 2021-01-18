package com.shoppiq.service;

import com.shoppiq.entity.AuthGroup;
import com.shoppiq.repository.AuthGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthGroupService {

    private final AuthGroupRepository authGroupRepository;

    public AuthGroupService(AuthGroupRepository authGroupRepository) {
        this.authGroupRepository = authGroupRepository;
    }

    public AuthGroup save(String username, String authGroup) {
        return authGroupRepository.save(new AuthGroup(username, authGroup));
    }
}
