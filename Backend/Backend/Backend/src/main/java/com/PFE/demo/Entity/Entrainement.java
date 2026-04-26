package com.academy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "entrainements")
@Data
@NoArgsConstructor
public class Entrainement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String lieu;

    @Column(name = "duree_heure")
    private int dureeHeure;

    @Column(name = "duree_minute")
    private int dureeMinute;

    private String description;

    @ManyToOne
    @JoinColumn(name = "entraineur_id")
    private Entraineur entraineur;

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;

    @Enumerated(EnumType.STRING)
    private StatutEntrainement statut = StatutEntrainement.PLANIFIE;

    public enum StatutEntrainement {
        PLANIFIE, EN_COURS, TERMINE, ANNULE
    }
}