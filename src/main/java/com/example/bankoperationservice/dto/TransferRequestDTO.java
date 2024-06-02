package com.example.bankoperationservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
public class TransferRequestDTO {
    private final String idFrom;
    private final String idTo;
    private final BigDecimal sum;
}
