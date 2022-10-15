package com.example.backend.controller;

import com.example.backend.model.CreateUserDto;
import com.example.backend.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/user")
public class UserController {

    // Regi 3
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        //Ask Security Context for User information
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    @GetMapping("/logout")
    public void logout(HttpSession session){
        // invalidate session

        session.invalidate();
    }

    // Regi 1
    @PostMapping("/register")
    public String register(@RequestBody CreateUserDto createUserDto){

        // Regi 4
        String username = userService.register(createUserDto);

        return username;

    }


}
