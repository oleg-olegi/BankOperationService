package com.example.bankoperationservice.controller;

import com.example.bankoperationservice.dto.UserDTO;
import com.example.bankoperationservice.exceptions.UserNotFoundException;
import com.example.bankoperationservice.service.UserServiceImpl;
import jakarta.validation.Valid;
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
    private final UserServiceImpl userService;

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            userService.updateUser(id, userDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
