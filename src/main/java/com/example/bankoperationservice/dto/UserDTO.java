package com.example.bankoperationservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

}
