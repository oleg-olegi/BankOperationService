package com.example.bankoperationservice.service.interfaces;

import com.example.bankoperationservice.dto.RegisterDTO;
import com.example.bankoperationservice.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    void createUser(RegisterDTO registerDTO);

    UserDTO getUserInfo(UserDTO userDTO);

    void updateUser(Long id, UserDTO updateUserDTO);

    void deleteUser(UserDTO userDTO);


    boolean checkUserExists(String login);
}
