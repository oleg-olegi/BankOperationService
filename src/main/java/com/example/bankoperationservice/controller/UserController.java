package com.example.bankoperationservice.controller;

import com.example.bankoperationservice.dto.UserDTO;
import com.example.bankoperationservice.exceptions.UserNotFoundException;
import com.example.bankoperationservice.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            logger.info("Trying to update user (Controller)");
            userService.updateUser(id, userDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/getUserInfo/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id) {
        try {
            logger.info("Trying to retrieve user info (Controller)");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String authUserName = authentication.getName();
            UserDTO userDTO = userService.getUserInfo(id, authUserName);
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException e) {
            logger.error("User with id '{}' not found", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (AuthorizationServiceException e) {
            logger.error("Unauthorized", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You do not have permission to view this user");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            logger.info("Trying to delete user with id {} (Controller)", id);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String authUserName = authentication.getName();
            userService.deleteUser(id, authUserName);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            logger.error("User with id '{}' not found", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (AuthorizationServiceException e) {
            logger.error("Unauthorized", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You do not have permission to view this user");
        }
    }
}

