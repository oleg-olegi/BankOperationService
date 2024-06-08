package com.example.bankoperationservice.service.interfaces;

import com.example.bankoperationservice.dto.RegisterDTO;
import com.example.bankoperationservice.model.UserData;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AuthService {
    boolean login(String login, String password);

    void register(RegisterDTO register);
}
