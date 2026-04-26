package com.academy.repository;

import com.academy.entity.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur, Long> {
    Optional<Joueur> findByUtilisateurId(Long utilisateurId);
    List<Joueur> findByStatutInscription(Joueur.StatutInscription statut);
    List<Joueur> findByCategorieAge(String categorieAge);
    List<Joueur> findByGroupeId(Long groupeId);
    long countByStatutInscription(Joueur.StatutInscription statut);
}