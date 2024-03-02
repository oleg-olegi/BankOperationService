package com.example.bankoperationservice.controller;

import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserData request) {
        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
