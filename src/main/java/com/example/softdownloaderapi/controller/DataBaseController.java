package com.example.softdownloaderapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DataBase")
public class DataBaseController {

    @GetMapping("/init")
    public String init(){
        return "Khởi tạo database thành công";
    }

}
