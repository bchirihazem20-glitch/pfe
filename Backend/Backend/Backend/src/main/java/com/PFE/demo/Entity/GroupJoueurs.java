package com.PFE.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_joueurs")
public class GroupJoueurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "joueur_id")
    private User joueur;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups group;

    public GroupJoueurs() {}

    public GroupJoueurs(User joueur, Groups group) {
        this.joueur = joueur;
        this.group = group;
    }

    public Long getId() { return id; }

    public User getJoueur() { return joueur; }
    public void setJoueur(User joueur) { this.joueur = joueur; }

    public Groups getGroup() { return group; }
    public void setGroup(Groups group) { this.group = group; }
}