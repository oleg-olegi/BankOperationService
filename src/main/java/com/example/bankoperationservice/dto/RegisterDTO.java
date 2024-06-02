package com.example.bankoperationservice.dto;

import com.example.bankoperationservice.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @Size(min = 2, max = 16, message = "The number of characters must be at least 2 and no more than 16")
    private String name;

    @Size(min = 2, max = 16, message = "The number of characters must be at least 2 and no more than 16")
    private String surname;

    @Size(min = 4, max = 32, message = "The number of characters must be at least 4 and no more than 32")
    private String userName;

    @Size(min = 8, max = 16, message = "The number of characters must be at least 8 and no more than 16")
    private String password;

    @Email(message = "Incorrect email format")
    private String email;

    @Pattern(regexp = "^(?:\\+375|80)\\s?\\(?\\d{2}\\)?\\s?\\d{2}(?:\\d[\\-\\s]\\d{2}[\\-\\s]\\d{2}|[\\-\\s]\\d{2}[\\-\\s]\\d{3}|\\d{5})$", message = "Invalid characters")
    private String phone;

    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4}", message = "Date must be in format dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    private Role role;
}
