package com.academy.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

public class AuthDto {

    @Data
    public static class RegisterRequest {
        @NotBlank(message = "Le nom est obligatoire")
        private String nom;

        @NotBlank(message = "Le prénom est obligatoire")
        private String prenom;

        @Email(message = "Email invalide")
        @NotBlank(message = "L'email est obligatoire")
        private String email;

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
        private String motDePasse;

        private String telephone;

        private String adresse;

        @NotNull(message = "La date de naissance est obligatoire")
        private LocalDate dateNaissance;

        @NotBlank(message = "Le nom du père est obligatoire")
        private String nomPere;

        @NotBlank(message = "Le téléphone du père est obligatoire")
        private String telephonePere;
    }

    @Data
    public static class LoginRequest {
        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String motDePasse;
    }

    @Data
    public static class AuthResponse {
        private String token;
        private String type = "Bearer";
        private Long id;
        private String nom;
        private String prenom;
        private String email;
        private String role;

        public AuthResponse(String token, Long id, String nom, String prenom, String email, String role) {
            this.token = token;
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.role = role;
        }
    }
}