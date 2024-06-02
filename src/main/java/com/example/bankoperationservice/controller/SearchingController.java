package com.example.bankoperationservice.controller;

import com.example.bankoperationservice.dto.UserDTO;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.service.SearchingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users/filter")
public class SearchingController {

    private final static Logger logger = LoggerFactory.getLogger(SearchingController.class);
    private SearchingService searchingService;

    @GetMapping
    public ResponseEntity<List<UserData>> getAllUsers(@RequestParam("page") Integer page,
                                                      @RequestParam("size") Integer size) {
        logger.info("Fetching all users with page {} and size {}", page, size);
        try {
            List<UserData> users = searchingService.getAll(page, size);
            logger.info("Successfully fetched {} users", users.size());
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Error fetching users", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<UserDTO>> getAllByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        logger.info("Fetching users by date {}", date);
        try {
            List<UserDTO> foundedUsers = searchingService.searchByDate(date);
            logger.info("Successfully fetched {} users by date {}", foundedUsers.size(), date);
            return ResponseEntity.ok(foundedUsers);
        } catch (Exception e) {
            logger.error("Error fetching users by date {}", date, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/by-phone")
    public ResponseEntity<UserDTO> getAllByPhone(@RequestParam String phone) {
        logger.info("Fetching user by phone {}", phone);
        try {
            UserDTO foundedContact = searchingService.searchByPhone(phone);
            logger.info("Successfully fetched user by phone {}", phone);
            return ResponseEntity.ok(foundedContact);
        } catch (Exception e) {
            logger.error("Error fetching user by phone {}", phone, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<UserDTO>> getAllByName(@RequestParam String name) {
        logger.info("Fetching users by name {}", name);
        try {
            List<UserDTO> foundedUsers = searchingService.searchByName(name);
            logger.info("Successfully fetched {} users by name {}", foundedUsers.size(), name);
            return ResponseEntity.ok(foundedUsers);
        } catch (Exception e) {
            logger.error("Error fetching users by name {}", name, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDTO> getAllByEmail(@RequestParam String email) {
        logger.info("Fetching user by email {}", email);
        try {
            UserDTO foundedUser = searchingService.searchByEmail(email);
            logger.info("Successfully fetched user by email {}", email);
            return ResponseEntity.ok(foundedUser);
        } catch (Exception e) {
            logger.error("Error fetching user by email {}", email, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

