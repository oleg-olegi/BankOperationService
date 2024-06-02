package com.example.bankoperationservice.service.interfaces;

import com.example.bankoperationservice.dto.RegisterDTO;

public interface AuthService {
    boolean login(String login, String password);

    void register(RegisterDTO register);
}
