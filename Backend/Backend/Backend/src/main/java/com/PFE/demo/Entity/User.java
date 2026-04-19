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

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // 🔥 Remplacement du String role par relation
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private LocalDate dateNaissance;

    // constructor empty
    public User() {}

    // constructor
    public User(String nom, String email, String password, Role role, LocalDate dateNaissance) {
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dateNaissance = dateNaissance;
    }

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupes groupe;

    public Groupes getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupes groupe) {
        this.groupe = groupe;
    }

    // getters & setters
    public Long getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // 🔥 Getter/Setter corrigés
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
}