package com.academy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "evaluations")
@Data
@NoArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    private Double note;

    private String commentaire;

    @Column(name = "vitesse")
    private Double vitesse;

    @Column(name = "technique")
    private Double technique;

    @Column(name = "physique")
    private Double physique;

    @Column(name = "mental")
    private Double mental;

    @ManyToOne
    @JoinColumn(name = "joueur_id")
    private Joueur joueur;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Entraineur coach;

    @ManyToOne
    @JoinColumn(name = "seance_test_id")
    private SeanceTest seanceTest;
}