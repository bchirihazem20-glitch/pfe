package com.PFE.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    // ✅ nouveau champ
    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // ✅ nouveau champ
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private LocalDate dateNaissance;

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupes groupe;

    // constructor vide
    public User() {}

    // ✅ constructor corrigé
    public User(String nom, String prenom, String email, String password,
                String telephone, Role role, LocalDate dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.role = role;
        this.dateNaissance = dateNaissance;
    }

    // getters & setters

    public Long getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    // ✅ prenom
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // ✅ telephone
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public Groupes getGroupe() { return groupe; }
    public void setGroupe(Groupes groupe) { this.groupe = groupe; }
}