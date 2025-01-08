package com.example.service_utilisateur.controller;

import com.example.service_utilisateur.dto.UserDto;
import com.example.service_utilisateur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")

public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return this.userService.getUserById(id);
    }
}
