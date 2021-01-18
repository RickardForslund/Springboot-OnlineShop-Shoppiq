package com.shoppiq.controller;

//import com.shoppiq.entity.AuthGroup;

import com.shoppiq.entity.AuthGroup;
import com.shoppiq.entity.Item;
import com.shoppiq.entity.User;
//import com.shoppiq.repository.AuthGroupRepository;
import com.shoppiq.repository.AuthGroupRepository;
import com.shoppiq.repository.ItemRepository;
import com.shoppiq.repository.UserRepository;
import com.shoppiq.service.ItemService;
import com.shoppiq.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

//@RestController
@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    //TODO move functionality to Service
    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthGroupRepository authGroupRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(UserService userService, UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @GetMapping("/create")
    public String showCreateUserForm(User user) {
        return "registration";
    }


    @PostMapping("/validate")
    public String validateUser(User user, BindingResult result, Model model) {
        if (result.hasErrors())
            return "error";
        int id = 0;
        return Integer.toString(id);
    }

    @PostMapping("/add")
    public String saveUser(User user, BindingResult result, Model model) {
        if (result.hasErrors())
            return "error";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        authGroupRepository.save(new AuthGroup(user.getUsername(), "USER"));
        userRepository.save(user);
//        authGroupRepository.save(new AuthGroup(user.getUsername(),"USER"));
        return "home";
    }

    @GetMapping("/list")
    public String showListItems(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "list-users";
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        authGroupRepository.save(new AuthGroup(user.getUsername(), "USER"));
        return userService.saveUser(user);
    }

    @GetMapping
    public Iterable<User> findAllUser() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/view/{username}")
    public String showUserByUsername(Model model, @PathVariable String username) {
        User user = null;
        try {
            user = userService.findByUsername(username).get();

        } catch (Exception exception) {
            model.addAttribute("errorMessage", "Contact not found");
        }

        model.addAttribute("user", user);
        return "user";
    }

    /*
        @GetMapping("/findbyusername/{username}")
        public Optional<User> findUserByUsername (@PathVariable String username){
            return userService.findByUsername(username);
        }
    */
    @PostMapping("/login")
    public String login(Model model, String username, String password) {
        var user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent())
            model.addAttribute("user", user.get());
        else
            System.out.println("ERROR"); //TODO make proper error
        return "user";
    }

}
