package com.shoppiq.service;

import com.shoppiq.entity.User;
import com.shoppiq.jms.service.RabbitMQSender;
import com.shoppiq.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RabbitMQSender rabbitMQSender;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, RabbitMQSender rabbitMQSender) {
        this.userRepository = userRepository;
        this.rabbitMQSender = rabbitMQSender;
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        rabbitMQSender.send(user);
        return userRepository.save(user);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }
}
