package com.example.bankoperationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTO {
    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
}
