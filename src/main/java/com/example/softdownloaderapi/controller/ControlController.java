package com.example.softdownloaderapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softdownloaderapi.model.InfoControl;
import com.example.softdownloaderapi.repository.CommentRepository;
import com.example.softdownloaderapi.repository.ParentCategoryRepository;
import com.example.softdownloaderapi.repository.SoftRepository;
import com.example.softdownloaderapi.repository.UserRepository;

@RestController
@RequestMapping("/control")
public class ControlController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ParentCategoryRepository parentCategoryRepository;
    @Autowired
    private SoftRepository softRepository;

    @GetMapping("/info")
    public InfoControl getHightestViewing(Integer amount){
        InfoControl result = new InfoControl();
        result.setTotalView(softRepository.getTotalView());
        result.setTotalPost(softRepository.getTotalSoft());
        result.setTotalUser(userRepository.getTotalUser());
        result.setTotalComment(commentRepository.getTotalComment());
        result.setTotalCategory(parentCategoryRepository.getTotalCategory());

        return result;
    }
}
