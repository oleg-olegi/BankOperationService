package com.example.bankoperationservice.controller;

import com.example.bankoperationservice.authConfiguration.sucurity.UserDetailServiceImpl;
import com.example.bankoperationservice.dto.JwtResponse;
import com.example.bankoperationservice.dto.LoginDTO;
import com.example.bankoperationservice.dto.RegisterDTO;
import com.example.bankoperationservice.exceptions.UserIsAlreadyExistsException;
import com.example.bankoperationservice.service.interfaces.AuthService;
import com.example.bankoperationservice.utils.JwtTokenUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthService authService;
    private final UserDetailServiceImpl userDetailService;
    private final AuthenticationManager authenticationManager;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDto, BindingResult result) {
        log.info("DTO controller {}", registerDto.toString());
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        try {
            authService.register(registerDto);
        } catch (UserIsAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "200", description = "Successfully login")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequestDTO) {
        logger.info("Received login request for user {}", loginRequestDTO.getUsername());
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userDetailService.loadUserByUsername(loginRequestDTO.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));

// на случай авторизации по логину и паролю
//            boolean isAuthenticated = authService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
//            if (isAuthenticated) {
//                logger.info("User {} logged in successfully", loginRequestDTO.getUsername());
//                return ResponseEntity.ok().build();
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//        } catch (IllegalArgumentException e) {
//            logger.error("Bad request: {}", e.getMessage());
//            return ResponseEntity.badRequest().build();
//        } catch (BadCredentialsException e) {
//            logger.error("Unauthorized: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        } catch (Exception e) {
//            logger.error("Internal server error: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }
}
