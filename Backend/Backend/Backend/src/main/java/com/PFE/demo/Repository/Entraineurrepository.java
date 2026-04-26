package com.academy.repository;

import com.academy.entity.Entraineur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EntraineurRepository extends JpaRepository<Entraineur, Long> {
    Optional<Entraineur> findByUtilisateurId(Long utilisateurId);
}