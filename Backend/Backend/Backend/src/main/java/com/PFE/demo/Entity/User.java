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

    @Column(unique = true)
    private String email;

    private String password;

    private String role = "client" ;
    // constructor empty
     private LocalDate dateNaissance;

    public User() {}

    // constructor
    public User(String nom, String email, String password,String role,LocalDate dateNaissance) {
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dateNaissance =dateNaissance;
    }

    // getters & setters
    public Long getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role= role; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance ) { this.dateNaissance= dateNaissance; }

}


