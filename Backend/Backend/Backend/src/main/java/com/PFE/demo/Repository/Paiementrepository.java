package com.academy.repository;
import com.academy.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByJoueurId(Long joueurId);
    List<Paiement> findByStatut(Paiement.StatutPaiement statut);
    Double sumMontantByStatut(Paiement.StatutPaiement statut);
}