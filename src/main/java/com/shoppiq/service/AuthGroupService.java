package com.shoppiq.service;

import com.shoppiq.entity.AuthGroup;
import com.shoppiq.jms.service.RabbitMQSender;
import com.shoppiq.repository.AuthGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthGroupService {

    private final AuthGroupRepository authGroupRepository;
    private final RabbitMQSender rabbitMQSender;

    public AuthGroupService(AuthGroupRepository authGroupRepository, RabbitMQSender rabbitMQSender) {
        this.authGroupRepository = authGroupRepository;
        this.rabbitMQSender = rabbitMQSender;
    }

    public AuthGroup save(String username, String authGroup) {
        AuthGroup group = new AuthGroup(username, authGroup);
        rabbitMQSender.send(group);
        return authGroupRepository.save(group);
    }
}
