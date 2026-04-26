package com.academy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "seances_test")
@Data
@NoArgsConstructor
public class SeanceTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime heure;

    @Column(nullable = false)
    private String lieu;

    @Column(name = "categorie_age", nullable = false)
    private String categorieAge; // U8, U10, U12, U14, U16, U18, Senior

    @Column(name = "age_min")
    private int ageMin;

    @Column(name = "age_max")
    private int ageMax;

    @Column(name = "niveau_performance")
    private String niveauPerformance; // Débutant, Intermédiaire, Avancé

    @Column(nullable = false)
    private String description;

    @Column(name = "capacite_max")
    private int capaciteMax;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Entraineur coach;

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;

    @Enumerated(EnumType.STRING)
    private StatutSeance statut = StatutSeance.OUVERT;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    public enum StatutSeance {
        OUVERT, COMPLET, TERMINE, ANNULE
    }
}