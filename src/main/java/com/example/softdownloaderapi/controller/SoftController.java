package com.example.softdownloaderapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softdownloaderapi.model.Soft;
import com.example.softdownloaderapi.repository.SoftRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/soft")
public class SoftController {
    @Autowired
    private SoftRepository softRepository;

    @GetMapping("/{idSoft}")
    public Soft getSoft(@PathVariable(value = "idSoft") Integer id){
        return softRepository.getSoft(id);
    }
}
