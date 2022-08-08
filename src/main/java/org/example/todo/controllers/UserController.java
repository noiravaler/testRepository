package org.example.todo.controllers;

import lombok.RequiredArgsConstructor;
import org.example.todo.configuration.SecurityConfig;
import org.example.todo.data.User;
import org.example.todo.storage.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/user")
public class UserController {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        user.setRole(SecurityConfig.USER_ROLE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userDao.save(user);
        newUser.setPassword("***");
        return newUser;
    }
}
