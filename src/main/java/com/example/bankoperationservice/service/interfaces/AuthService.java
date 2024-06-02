package com.example.bankoperationservice.service.interfaces;

import com.example.bankoperationservice.dto.RegisterDTO;
import com.example.bankoperationservice.model.UserData;

import java.util.Optional;

public interface AuthService {
    boolean login(String login, String password);

    void register(RegisterDTO register);

    UserData loadUserByUserName(String username);
}
