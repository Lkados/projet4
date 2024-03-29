package com.chatop.api.controller;

import com.chatop.api.dto.LoginDTO;
import com.chatop.api.dto.RegisterDTO;
import com.chatop.api.dto.UserDTO;
import com.chatop.api.service.JWTService;
import com.chatop.api.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JWTService jwtService;
    private final UserService userService;

    public AuthController(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDTO registerDTO) {
        // Check if the email is already in use
        if (userService.emailExists(registerDTO.getEmail())) {
            System.out.println("Email already in use");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Email already in use"));
        }
        System.out.println(registerDTO.getEmail());
        userService.registerNewUser(registerDTO);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(registerDTO.getEmail());
        loginDTO.setPassword(registerDTO.getPassword());
        Authentication authentication = userService.authenticate(loginDTO);
        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = userService.authenticate(loginDTO);
            String token = jwtService.generateToken(authentication);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        UserDTO userDTO = userService.getCurrentUser(authentication);
        return ResponseEntity.ok(userDTO);
    }
}
