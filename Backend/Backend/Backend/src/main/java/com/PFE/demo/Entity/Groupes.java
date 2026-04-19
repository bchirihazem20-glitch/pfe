package com.PFE.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groupes")
public class Groupes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle;

    private String description;

    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public Groupes() {
    }

    public Groupes(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}