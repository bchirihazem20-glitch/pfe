package com.PFE.demo.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private User coach;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupJoueurs> joueurs;

    public Groups() {}

    public Groups(String libelle, User coach) {
        this.libelle = libelle;
        this.coach = coach;
    }

    public Long getId() { return id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public User getCoach() { return coach; }
    public void setCoach(User coach) { this.coach = coach; }

    public List<GroupJoueurs> getJoueurs() { return joueurs; }
    public void setJoueurs(List<GroupJoueurs> joueurs) { this.joueurs = joueurs; }
}