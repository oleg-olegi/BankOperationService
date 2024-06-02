package com.example.bankoperationservice.service.interfaces;

import com.example.bankoperationservice.dto.RegisterDTO;

public interface UserService {
    void createUser(RegisterDTO registerDTO);

    boolean checkUserForRegisterOk(String login);

    boolean checkUserExists(String login);
}
