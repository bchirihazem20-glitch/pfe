package com.academy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "entraineurs")
@Data
@NoArgsConstructor
public class Entraineur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    private String grade;

    @OneToMany(mappedBy = "entraineur", cascade = CascadeType.ALL)
    private List<Entrainement> entrainements;

    @ManyToMany
    @JoinTable(
            name = "entraineur_groupe",
            joinColumns = @JoinColumn(name = "entraineur_id"),
            inverseJoinColumns = @JoinColumn(name = "groupe_id")
    )
    private List<Groupe> groupes;

    public void planifierSeance(Entrainement e) {}
    public void modifierSeance(Entrainement e) {}
    public void annulerSeance(Entrainement e) {}
}