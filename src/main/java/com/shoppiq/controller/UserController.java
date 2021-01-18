package com.shoppiq.controller;

import com.shoppiq.entity.User;
import com.shoppiq.service.AuthGroupService;
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

    //TODO move functionality to Service
    private final UserService userService;
    private final AuthGroupService authGroupService;

    public UserController(UserService userService, AuthGroupService authGroupService) {
        this.userService = userService;
        this.authGroupService = authGroupService;
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
        authGroupService.save(user.getUsername(), "USER");
        userService.save(user);
        return "home";
    }

    @GetMapping("/list")
    public String showListItems(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "list-users";
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        authGroupService.save(user.getUsername(), "USER");
        return userService.save(user);
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
        var user = userService.findByUsernameAndPassword(username, password);
        if (user.isPresent())
            model.addAttribute("user", user.get());
        else
            System.out.println("No user found"); //TODO make proper error
        return "user";
    }

}
