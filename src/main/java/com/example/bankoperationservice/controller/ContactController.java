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
        logger.info("Adding phone {} to contact with ID {}", phone, id);
        try {
            contactService.addPhone(id, phone);
            logger.info("Successfully added phone {} to contact with ID {}", phone, id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            logger.error("Error adding phone {} to contact with ID {}", phone, id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addEmail/{id}")
    public ResponseEntity<Void> addEmail(@PathVariable Long id, @RequestBody String email) {
        logger.info("Adding email {} to contact with ID {}", email, id);
        try {
            contactService.addEmail(id, email);
            logger.info("Successfully added email {} to contact with ID {}", email, id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            logger.error("Error adding email {} to contact with ID {}", email, id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update-phone/{id}")
    public ResponseEntity<Void> updatePhone(@PathVariable Long id, @RequestBody String newPhone) {
        logger.info("Updating phone for contact with ID {} to {}", id, newPhone);
        try {
            contactService.updatePhone(id, newPhone);
            logger.info("Successfully updated phone for contact with ID {} to {}", id, newPhone);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            logger.error("Error updating phone for contact with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update-email/{id}")
    public ResponseEntity<Void> updateEmail(@PathVariable Long id, @RequestBody String newEmail) {
        logger.info("Updating email for contact with ID {} to {}", id, newEmail);
        try {
            contactService.updateEmail(id, newEmail);
            logger.info("Successfully updated email for contact with ID {} to {}", id, newEmail);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            logger.error("Error updating email for contact with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete-phone/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id, @RequestBody String phone) {
        logger.info("Deleting phone {} from contact with ID {}", phone, id);
        try {
            contactService.deletePhone(id, phone);
            logger.info("Successfully deleted phone {} from contact with ID {}", phone, id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            logger.error("Error deleting phone {} from contact with ID {}", phone, id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete-email/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id, @RequestBody String email) {
        logger.info("Deleting email {} from contact with ID {}", email, id);
        try {
            contactService.deleteEmail(id, email);
            logger.info("Successfully deleted email {} from contact with ID {}", email, id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            logger.error("Error deleting email {} from contact with ID {}", email, id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

