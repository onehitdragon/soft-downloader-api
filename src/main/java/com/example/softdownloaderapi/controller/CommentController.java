package com.example.softdownloaderapi.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/to/{idSoft}")
    public ResponseEntity<ResponseMessage> commentTo(@PathVariable(value = "idSoft") @NotBlank Integer id, @NotBlank Integer commenterId, @NotBlank String content){
        Comment comment = new Comment();
        comment.setContent(content);
        Comment result = commentRepository.insert(id, commenterId, comment);

        return new ResponseEntity<ResponseMessage>(
            new ResponseMessageWithOption<Comment>("success", "", result),
            HttpStatus.OK
        );
    }

    @PostMapping("/replyto/{idSoft}")
    public ResponseEntity<ResponseMessage> commentTo(@PathVariable(value = "idSoft") @NotBlank Integer id, @NotBlank Integer commenterId, @NotBlank String content, @NotBlank Integer receivedCommentId){
        Comment comment = new Comment();
        comment.setContent(content);
        Comment result = commentRepository.insertReply(id, commenterId, comment, receivedCommentId);

        return new ResponseEntity<ResponseMessage>(
            new ResponseMessageWithOption<Comment>("success", "", result),
            HttpStatus.OK
        );
    }
}
