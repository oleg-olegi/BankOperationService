package com.example.bankoperationservice.service;

import com.example.bankoperationservice.dto.RegisterDTO;
import com.example.bankoperationservice.exceptions.UserNotFoundException;
import com.example.bankoperationservice.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Lazy
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);


    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;

    @Override
    public void register(RegisterDTO registerDTO) {
        logger.info("Trying user registration");
        registerDTO.setPassword(encoder.encode(registerDTO.getPassword()));
        userService.createUser(registerDTO);
    }

    @Override
    @Transactional
    public boolean login(String login, String password) {
        if (password == null) {
            logger.error("Password cannot be null for login: {}", login);
            throw new IllegalArgumentException("Password cannot be null");
        }
        if (userService.checkUserExists(login)) {
            try {
                logger.info("Trying to authenticate user {}", login);
                Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(login, password));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("User {} authenticated successfully", login);

                authentication = SecurityContextHolder.getContext().getAuthentication();
                logger.info("ContextHolder {}", authentication);

                return authentication.isAuthenticated();
            } catch (Exception e) {
                logger.error("Authentication failed for user {}: {}", login, e.getMessage());
                throw new BadCredentialsException("Invalid credentials", e);
            }
        }
        logger.error("User not found for login: {}", login);
        throw new UserNotFoundException("User not found");
    }
}