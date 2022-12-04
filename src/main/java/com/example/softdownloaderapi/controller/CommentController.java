package com.example.softdownloaderapi.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.softdownloaderapi.model.*;

import com.example.softdownloaderapi.repository.CommentRepository;

@RestController
@RequestMapping("/comment")
public class CommentController{
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/getall/{idSoft}")
    public List<Comment> getCommentsBySoft(@PathVariable(value = "idSoft") @NotBlank Integer id){
        return commentRepository.getCommentsBySoft(id);
    }
}
