package com.academy.repository;

import com.academy.entity.Entrainement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntrainementRepository extends JpaRepository<Entrainement, Long> {
    List<Entrainement> findByGroupeId(Long groupeId);
    List<Entrainement> findByEntraineurId(Long entraineurId);
}