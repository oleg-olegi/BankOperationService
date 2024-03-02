package com.example.bankoperationservice.controller;

import com.example.bankoperationservice.model.Contact;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.service.SearchingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping("/by-date")
    public ResponseEntity<List<UserData>> getAllByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<UserData> foundedUsers = searchingService.searchByDate(date);
        return ResponseEntity.ok(foundedUsers);
    }

    @GetMapping("/by-phone")
    public ResponseEntity<Contact> getAllByPhone(@RequestParam String phone) {
        Contact foundedContact = searchingService.searchByPhone(phone);
        return ResponseEntity.ok(foundedContact);
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<UserData>> getAllByName(@RequestParam String name) {
        List<UserData> foundedUsers = searchingService.searchByName(name);
        return ResponseEntity.ok(foundedUsers);
    }
    @GetMapping("/by-email")
    public ResponseEntity<Contact> getAllByEmail(@RequestParam String email) {
        Contact foundedUsers = searchingService.searchByEmail(email);
        return ResponseEntity.ok(foundedUsers);
    }
}
