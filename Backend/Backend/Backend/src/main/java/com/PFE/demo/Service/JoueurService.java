package com.academy.service;

import com.academy.entity.*;
import com.academy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class JoueurService {

    @Autowired private JoueurRepository joueurRepository;
    @Autowired private PaiementRepository paiementRepository;
    @Autowired private EvaluationRepository evaluationRepository;
    @Autowired private AvisRepository avisRepository;
    @Autowired private SeanceTestRepository seanceTestRepository;
    @Autowired private UtilisateurRepository utilisateurRepository;

    public Map<String, Object> getDashboardStats(Long utilisateurId) {
        Joueur joueur = joueurRepository.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        Map<String, Object> stats = new HashMap<>();
        stats.put("statutInscription", joueur.getStatutInscription());
        stats.put("categorieAge", joueur.getCategorieAge());
        stats.put("note", joueur.getNote());
        stats.put("groupe", joueur.getGroupe() != null ? joueur.getGroupe().getNom() : null);
        stats.put("totalPaiements", paiementRepository.findByJoueurId(joueur.getId()).size());
        stats.put("totalEvaluations", evaluationRepository.findByJoueurId(joueur.getId()).size());
        return stats;
    }

    public Joueur getMonProfil(Long utilisateurId) {
        return joueurRepository.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
    }

    public List<Evaluation> getMesEvaluations(Long utilisateurId) {
        Joueur joueur = joueurRepository.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        return evaluationRepository.findByJoueurId(joueur.getId());
    }

    public List<Paiement> getMesPaiements(Long utilisateurId) {
        Joueur joueur = joueurRepository.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        return paiementRepository.findByJoueurId(joueur.getId());
    }

    public Paiement effectuerPaiement(Long utilisateurId, Double montant, String moisConcerne) {
        Joueur joueur = joueurRepository.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        Paiement paiement = new Paiement();
        paiement.setJoueur(joueur);
        paiement.setMontant(montant);
        paiement.setMoisConcerne(moisConcerne);
        paiement.setDatePaiement(LocalDate.now());
        paiement.setStatut(Paiement.StatutPaiement.EN_ATTENTE);
        return paiementRepository.save(paiement);
    }

    public Avis ajouterAvis(Long utilisateurId, String avisTexte, Integer note) {
        Utilisateur user = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Avis avis = new Avis();
        avis.setUtilisateur(user);
        avis.setAvis(avisTexte);
        avis.setNote(note);
        avis.setApproved(false);
        return avisRepository.save(avis);
    }

    public List<SeanceTest> getSeancesTestDisponibles(String categorieAge) {
        if (categorieAge != null) {
            return seanceTestRepository.findByCategorieAge(categorieAge);
        }
        return seanceTestRepository.findByStatut(SeanceTest.StatutSeance.OUVERT);
    }
}