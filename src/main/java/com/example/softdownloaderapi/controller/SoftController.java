package com.example.softdownloaderapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softdownloaderapi.model.Soft;
import com.example.softdownloaderapi.model.User;
import com.example.softdownloaderapi.repository.SoftRepository;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/soft")
@Validated
public class SoftController {
    @Autowired
    private SoftRepository softRepository;

    @GetMapping("/{idSoft}")
    public Soft getSoft(@PathVariable(value = "idSoft") Integer id){
        return softRepository.getSoft(id);
    }

    @PostMapping("/add")
    public Soft addSoft(@NotBlank String title, @NotBlank String content,
        @NotBlank @Min(1) String authorId){
        Soft soft = new Soft();

        User user = new User();
        user.setId(authorId);

        soft.setTitle(title);
        soft.setAuthor(user);
        soft.setContent(content);
        soft.setAmountView(0);
        soft.setCreateDate(new Date());

        return softRepository.insert(soft);
    }

    @DeleteMapping("/{idSoft}")
    public void deleteSoft(@PathVariable(value = "idSoft") @Min(1) String id){
        softRepository.delete(Integer.parseInt(id));
    }
}
