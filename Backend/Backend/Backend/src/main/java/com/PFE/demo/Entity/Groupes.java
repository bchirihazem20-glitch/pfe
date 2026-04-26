package com.academy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "groupes")
@Data
@NoArgsConstructor
public class Groupe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String categorie;  // U8, U10, U12, U14, U16, U18, Senior

    private String saison;

    @OneToMany(mappedBy = "groupe")
    private List<Joueur> joueurs;

    @ManyToMany(mappedBy = "groupes")
    private List<Entraineur> entraineurs;

    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
    private List<Entrainement> entrainements;

    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
    private List<SeanceTest> seancesTest;
}