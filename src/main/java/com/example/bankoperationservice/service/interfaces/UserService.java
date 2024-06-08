package com.example.bankoperationservice.service.interfaces;

import com.example.bankoperationservice.dto.RegisterDTO;
import com.example.bankoperationservice.dto.UserDTO;

public interface UserService {
    void createUser(RegisterDTO registerDTO);

    UserDTO getUserInfo(Long id, String username);

    void updateUser(Long id, UserDTO updateUserDTO);

    void deleteUser(UserDTO userDTO);


    boolean checkUserExists(String login);
}
