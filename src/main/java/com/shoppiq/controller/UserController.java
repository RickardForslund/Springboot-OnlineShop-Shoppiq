package com.shoppiq.controller;

import com.shoppiq.entity.Item;
import com.shoppiq.entity.User;
import com.shoppiq.repository.ItemRepository;
import com.shoppiq.repository.UserRepository;
import com.shoppiq.service.ItemService;
import com.shoppiq.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@RestController
@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/create")
    public String showCreateUserForm(User user) {
        return "create-item";
    }


    @PostMapping("/add")
    public String saveUser(User user, BindingResult result, Model model) {
        if (result.hasErrors())
            return "error";
        userRepository.save(user);
        return "home";
    }

/*
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public Iterable<User> findAllUser() {
        return userService.findAllUsers();
    }

 */

        @GetMapping("/{id}")
        public Optional<User> findUserById (@PathVariable Long id){
            return userService.findById(id);
        }

}
