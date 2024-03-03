package com.example.bankoperationservice.controller;

import com.example.bankoperationservice.dto.DTO;
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

    @GetMapping
    public ResponseEntity<List<UserData>> getAllUsers(@RequestParam("page") Integer page,
                                                 @RequestParam("size") Integer size) {
        return ResponseEntity.ok(searchingService.getAll(page, size));
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<DTO>> getAllByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<DTO> foundedUsers = searchingService.searchByDate(date);
        return ResponseEntity.ok(foundedUsers);
    }

    @GetMapping("/by-phone")
    public ResponseEntity<DTO> getAllByPhone(@RequestParam String phone) {
        DTO foundedContact = searchingService.searchByPhone(phone);
        return ResponseEntity.ok(foundedContact);
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<DTO>> getAllByName(@RequestParam String name) {
        List<DTO> foundedUsers = searchingService.searchByName(name);
        return ResponseEntity.ok(foundedUsers);
    }

    @GetMapping("/by-email")
    public ResponseEntity<DTO> getAllByEmail(@RequestParam String email) {
        DTO foundedUsers = searchingService.searchByEmail(email);
        return ResponseEntity.ok(foundedUsers);
    }
}
