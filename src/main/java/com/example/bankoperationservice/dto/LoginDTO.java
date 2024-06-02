package com.example.bankoperationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @Size(min = 4, max = 32, message = "The number of characters must be at least 4 and no more than 32")
    @JsonProperty("username")
    private String username;

    @Size(min = 8, max = 16, message = "The number of characters must be at least 8  and no more than 16")
    private String password;
}