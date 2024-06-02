package com.example.bankoperationservice.controller;

import com.example.bankoperationservice.dto.TransferRequestDTO;
import com.example.bankoperationservice.service.TransferService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TransferController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private TransferService transferService;

    @PreAuthorize("hasAuthority('basic_user_access')")
    @PostMapping("/transfer")
    public ResponseEntity<?> doTransfer(@RequestBody TransferRequestDTO transferRequestDTO) {
        logger.info("Received transfer request from account {} to account {} for amount {}",
                transferRequestDTO.getIdFrom(), transferRequestDTO.getIdTo(), transferRequestDTO.getSum());
        try {
            transferService.transferMoney(transferRequestDTO.getIdFrom(), transferRequestDTO.getIdTo(),
                    transferRequestDTO.getSum());
            logger.info("Successfully transferred amount {} from account {} to account {}",
                    transferRequestDTO.getSum(), transferRequestDTO.getIdFrom(), transferRequestDTO.getIdTo());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error during transfer from account {} to account {} for amount {}",
                    transferRequestDTO.getIdFrom(), transferRequestDTO.getIdTo(), transferRequestDTO.getSum(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
