package com.example.softdownloaderapi.controller;

import javax.validation.constraints.NotBlank;

import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softdownloaderapi.model.CheckingUserResult;
import com.example.softdownloaderapi.model.ErrorValidateMessage;
import com.example.softdownloaderapi.model.ResponseMessage;
import com.example.softdownloaderapi.model.User;
import com.example.softdownloaderapi.model.Role;
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

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(@NotBlank String username, @NotBlank String password, @NotBlank String fullname){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullname);
        user.setRole(new Role("2"));

        CheckingUserResult userResult = userRepository.checkUser(user);
        if(userResult.isResult()){
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("error", "username is exist"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("success"), HttpStatus.OK);
    }
}
