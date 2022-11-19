package com.example.softdownloaderapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softdownloaderapi.model.CheckingUserResult;
import com.example.softdownloaderapi.model.User;
import com.example.softdownloaderapi.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/check")
    public CheckingUserResult check(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return userRepository.checkUser(user);
    }
}
