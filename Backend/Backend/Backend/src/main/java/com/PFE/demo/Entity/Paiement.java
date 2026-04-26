package com.academy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "paiements")
@Data
@NoArgsConstructor
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @Column(nullable = false)
    private Double montant;

    @Column(name = "mois_concerne")
    private String moisConcerne;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut = StatutPaiement.EN_ATTENTE;

    private String description;

    @ManyToOne
    @JoinColumn(name = "joueur_id")
    private Joueur joueur;

    public enum StatutPaiement {
        EN_ATTENTE, PAYE, REFUSE
    }
}