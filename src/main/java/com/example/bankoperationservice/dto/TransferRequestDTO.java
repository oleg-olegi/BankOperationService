package com.example.bankoperationservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
public class TransferRequestDTO {
    private final String idFrom;
    private final String idTo;
    private final BigDecimal sum;
}
