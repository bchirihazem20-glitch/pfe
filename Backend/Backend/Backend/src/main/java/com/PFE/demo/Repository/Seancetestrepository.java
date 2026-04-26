package com.academy.repository;

import com.academy.entity.SeanceTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeanceTestRepository extends JpaRepository<SeanceTest, Long> {
    List<SeanceTest> findByCategorieAge(String categorieAge);
    List<SeanceTest> findByCoachId(Long coachId);
    List<SeanceTest> findByStatut(SeanceTest.StatutSeance statut);
    List<SeanceTest> findByNiveauPerformance(String niveauPerformance);
}