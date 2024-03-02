package com.example.bankoperationservice.controller;

import com.example.bankoperationservice.service.ContactService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/contacts")
public class ContactController {
    private final static Logger logger = LoggerFactory.getLogger(ContactController.class);
    private ContactService contactService;

    @PostMapping("/addPhone/{id}")
    public ResponseEntity<Void> addPhone(@PathVariable Long id, @RequestBody String phone) {
        contactService.addPhone(id, phone);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/addEmail/{id}")
    public ResponseEntity<Void> addEmail(@PathVariable Long id, @RequestBody String email) {
        contactService.addEmail(id, email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update-phone/{id}")
    public ResponseEntity<Void> updatePhone(@PathVariable Long id, @RequestBody String newPhone) {
        contactService.updatePhone(id, newPhone);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update-email/{id}")
    public ResponseEntity<Void> updateEmail(@PathVariable Long id, @RequestBody String newEmail) {
        contactService.updateEmail(id, newEmail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete-phone/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id, @RequestBody String phone) {
        contactService.deletePhone(id, phone);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete-email/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id, @RequestBody String email) {
        contactService.deleteEmail(id, email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
