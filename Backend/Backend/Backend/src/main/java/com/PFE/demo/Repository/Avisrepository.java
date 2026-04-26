package com.academy.repository;
import com.academy.entity.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findByIsApproved(boolean approved);
    List<Avis> findByUtilisateurId(Long utilisateurId);
}