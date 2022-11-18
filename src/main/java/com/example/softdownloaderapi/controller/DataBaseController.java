package com.example.softdownloaderapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softdownloaderapi.repository.ParentCategoryRepository;
import com.example.softdownloaderapi.model.ParentCategory;

@RestController
@RequestMapping("/DataBase")
public class DataBaseController {
    @Autowired
    private ParentCategoryRepository parentCategoryRepository;

    @GetMapping("/init")
    public List<ParentCategory> init(){
        return parentCategoryRepository.getAll();
    }

    @GetMapping("/init2")
    public String init2(){
        return "vinh";
    }
}
