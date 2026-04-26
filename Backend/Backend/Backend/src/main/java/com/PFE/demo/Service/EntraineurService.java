package com.academy.service;

import com.academy.entity.*;
import com.academy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class CoachService {

    @Autowired private EntraineurRepository entraineurRepository;
    @Autowired private JoueurRepository joueurRepository;
    @Autowired private EntrainementRepository entrainementRepository;
    @Autowired private SeanceTestRepository seanceTestRepository;
    @Autowired private EvaluationRepository evaluationRepository;
    @Autowired private GroupeRepository groupeRepository;

    public Map<String, Object> getDashboardStats(Long utilisateurId) {
        Entraineur coach = entraineurRepository.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Coach non trouvé"));
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalEntrainements", entrainementRepository.findByEntraineurId(coach.getId()).size());
        stats.put("totalSeancesTest", seanceTestRepository.findByCoachId(coach.getId()).size());
        stats.put("totalEvaluations", evaluationRepository.findByCoachId(coach.getId()).size());
        return stats;
    }

    public SeanceTest planifierSeanceTest(Long utilisateurId, String categorieAge,
                                          LocalDate date, LocalTime heure, String lieu,
                                          String description, int capaciteMax,
                                          String niveauPerformance, Long groupeId) {
        Entraineur coach = entraineurRepository.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Coach non trouvé"));

        int[] ageLimits = getAgeLimits(categorieAge);

        SeanceTest seance = new SeanceTest();
        seance.setCoach(coach);
        seance.setCategorieAge(categorieAge);
        seance.setDate(date);
        seance.setHeure(heure);
        seance.setLieu(lieu);
        seance.setDescription(description);
        seance.setCapaciteMax(capaciteMax);
        seance.setNiveauPerformance(niveauPerformance);
        seance.setAgeMin(ageLimits[0]);
        seance.setAgeMax(ageLimits[1]);
        seance.setStatut(SeanceTest.StatutSeance.OUVERT);

        if (groupeId != null) {
            groupeRepository.findById(groupeId).ifPresent(seance::setGroupe);
        }

        return seanceTestRepository.save(seance);
    }

    public List<SeanceTest> getMesSeancesTest(Long utilisateurId) {
        Entraineur coach = entraineurRepository.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Coach non trouvé"));
        return seanceTestRepository.findByCoachId(coach.getId());
    }

    public List<SeanceTest> getAllSeancesTestOuvertes() {
        return seanceTestRepository.findByStatut(SeanceTest.StatutSeance.OUVERT);
    }

    public Evaluation evaluerJoueur(Long utilisateurId, Long joueurId, Long seanceTestId,
                                    double vitesse, double technique, double physique,
                                    double mental, String commentaire) {
        Entraineur coach = entraineurRepository.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Coach non trouvé"));
        Joueur joueur = joueurRepository.findById(joueurId)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé"));

        double note = (vitesse + technique + physique + mental) / 4.0;

        Evaluation eval = new Evaluation();
        eval.setCoach(coach);
        eval.setJoueur(joueur);
        eval.setDate(LocalDate.now());
        eval.setVitesse(vitesse);
        eval.setTechnique(technique);
        eval.setPhysique(physique);
        eval.setMental(mental);
        eval.setNote(note);
        eval.setCommentaire(commentaire);

        if (seanceTestId != null) {
            seanceTestRepository.findById(seanceTestId).ifPresent(eval::setSeanceTest);
        }

        // Update joueur note
        joueur.setNote(note);
        joueurRepository.save(joueur);

        return evaluationRepository.save(eval);
    }

    public List<Joueur> getJoueursByCategorie(String categorieAge) {
        return joueurRepository.findByCategorieAge(categorieAge);
    }

    public Entrainement planifierEntrainement(Long utilisateurId, Long groupeId,
                                              LocalDate date, String lieu,
                                              int dureeHeure, int dureeMinute, String description) {
        Entraineur coach = entraineurRepository.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Coach non trouvé"));
        Groupe groupe = groupeRepository.findById(groupeId)
                .orElseThrow(() -> new RuntimeException("Groupe non trouvé"));

        Entrainement entrainement = new Entrainement();
        entrainement.setEntraineur(coach);
        entrainement.setGroupe(groupe);
        entrainement.setDate(date);
        entrainement.setLieu(lieu);
        entrainement.setDureeHeure(dureeHeure);
        entrainement.setDureeMinute(dureeMinute);
        entrainement.setDescription(description);

        return entrainementRepository.save(entrainement);
    }

    private int[] getAgeLimits(String categorie) {
        return switch (categorie) {
            case "U6"  -> new int[]{4, 6};
            case "U8"  -> new int[]{7, 8};
            case "U10" -> new int[]{9, 10};
            case "U12" -> new int[]{11, 12};
            case "U14" -> new int[]{13, 14};
            case "U16" -> new int[]{15, 16};
            case "U18" -> new int[]{17, 18};
            default    -> new int[]{19, 99};
        };
    }
}