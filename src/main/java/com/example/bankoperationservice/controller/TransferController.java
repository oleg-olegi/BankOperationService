package com.example.bankoperationservice.controller;

import com.example.bankoperationservice.service.TransferService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TransferController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private TransferService transferService;

    @PutMapping("/transfer/from/{idFrom}/to{idTo}/amount/{sum}")
    public ResponseEntity<Void> doTransfer(@PathVariable Long idFrom, @PathVariable Long idTo,
                                           @PathVariable BigDecimal sum) {
        transferService.transferMoney(idFrom, idTo, sum);
        return ResponseEntity.ok().build();
    }
}
