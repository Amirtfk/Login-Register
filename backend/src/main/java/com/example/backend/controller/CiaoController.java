package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ciao")
public class CiaoController {

    @GetMapping
    public String sayHello(){
        return "Ciao!";
    }
}
