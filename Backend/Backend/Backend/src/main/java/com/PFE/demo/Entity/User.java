package com.academy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    private String telephone;

    private String adresse;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "nom_pere")
    private String nomPere;

    @Column(name = "telephone_pere")
    private String telephonePere;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.JOUEUR;

    @Column(name = "is_active")
    private boolean isActive = false;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    public enum Role {
        JOUEUR, COACH, ADMIN
    }
}