package com.example.softdownloaderapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softdownloaderapi.repository.ParentCategoryRepository;
import com.example.softdownloaderapi.model.ParentCategory;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ParentCategoryRepository parentCategoryRepository;

    @GetMapping("/getall")
    public List<ParentCategory> getAll(){
        return parentCategoryRepository.getAll();
    }
}
