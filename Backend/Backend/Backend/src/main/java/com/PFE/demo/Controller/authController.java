package com.academy.controller;

import com.academy.dto.AuthDto;
import com.academy.entity.Utilisateur;
import com.academy.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDto.LoginRequest request) {
        try {
            AuthDto.AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email ou mot de passe incorrect"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthDto.RegisterRequest request) {
        try {
            Utilisateur user = authService.register(request);
            return ResponseEntity.ok(Map.of(
                    "message", "Inscription réussie! Votre compte est en attente de validation.",
                    "userId", user.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}