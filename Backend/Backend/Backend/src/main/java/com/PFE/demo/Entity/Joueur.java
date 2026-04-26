package com.academy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "joueurs")
@Data
@NoArgsConstructor
public class Joueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    private String poste;

    @Column(name = "categorie_age")
    private String categorieAge;

    private Double note;

    @Column(name = "statut_inscription")
    @Enumerated(EnumType.STRING)
    private StatutInscription statutInscription = StatutInscription.EN_ATTENTE;

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;

    @OneToMany(mappedBy = "joueur", cascade = CascadeType.ALL)
    private List<Paiement> paiements;

    @OneToMany(mappedBy = "joueur", cascade = CascadeType.ALL)
    private List<Evaluation> evaluations;

    public enum StatutInscription {
        EN_ATTENTE, ACCEPTE, REFUSE
    }
}