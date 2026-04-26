package com.academy.service;

import com.academy.entity.*;
import com.academy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired private UtilisateurRepository utilisateurRepository;
    @Autowired private JoueurRepository joueurRepository;
    @Autowired private EntraineurRepository entraineurRepository;
    @Autowired private GroupeRepository groupeRepository;
    @Autowired private PaiementRepository paiementRepository;
    @Autowired private AvisRepository avisRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalJoueurs", joueurRepository.count());
        stats.put("totalCoachs", entraineurRepository.count());
        stats.put("totalGroupes", groupeRepository.count());
        stats.put("joueurEnAttente", joueurRepository.countByStatutInscription(Joueur.StatutInscription.EN_ATTENTE));
        stats.put("paiementsEnAttente", paiementRepository.countByStatut(Paiement.StatutPaiement.EN_ATTENTE));
        stats.put("avisEnAttente", avisRepository.countByIsApproved(false));
        return stats;
    }

    public List<Joueur> getJoueursEnAttente() {
        return joueurRepository.findByStatutInscription(Joueur.StatutInscription.EN_ATTENTE);
    }

    public Joueur accepterJoueur(Long joueurId) {
        Joueur joueur = joueurRepository.findById(joueurId)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        joueur.setStatutInscription(Joueur.StatutInscription.ACCEPTE);
        joueur.getUtilisateur().setActive(true);
        utilisateurRepository.save(joueur.getUtilisateur());
        return joueurRepository.save(joueur);
    }

    public Joueur refuserJoueur(Long joueurId) {
        Joueur joueur = joueurRepository.findById(joueurId)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        joueur.setStatutInscription(Joueur.StatutInscription.REFUSE);
        return joueurRepository.save(joueur);
    }

    public Utilisateur creerCoach(String nom, String prenom, String email, String grade) {
        Utilisateur user = new Utilisateur();
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setEmail(email);
        user.setMotDePasse(passwordEncoder.encode("coach123"));
        user.setRole(Utilisateur.Role.COACH);
        user.setActive(true);
        user.setCreatedAt(LocalDate.now());
        Utilisateur saved = utilisateurRepository.save(user);

        Entraineur entraineur = new Entraineur();
        entraineur.setUtilisateur(saved);
        entraineur.setGrade(grade);
        entraineurRepository.save(entraineur);

        return saved;
    }

    public void supprimerCoach(Long utilisateurId) {
        entraineurRepository.findByUtilisateurId(utilisateurId)
                .ifPresent(e -> entraineurRepository.delete(e));
        utilisateurRepository.deleteById(utilisateurId);
    }

    public Groupe creerGroupe(String nom, String categorie, String saison) {
        Groupe groupe = new Groupe();
        groupe.setNom(nom);
        groupe.setCategorie(categorie);
        groupe.setSaison(saison);
        return groupeRepository.save(groupe);
    }

    public Joueur affecterJoueurGroupe(Long joueurId, Long groupeId) {
        Joueur joueur = joueurRepository.findById(joueurId)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        Groupe groupe = groupeRepository.findById(groupeId)
                .orElseThrow(() -> new RuntimeException("Groupe non trouvé"));
        joueur.setGroupe(groupe);
        return joueurRepository.save(joueur);
    }

    public List<Avis> getAvisEnAttente() {
        return avisRepository.findByIsApproved(false);
    }

    public Avis approuverAvis(Long avisId) {
        Avis avis = avisRepository.findById(avisId)
                .orElseThrow(() -> new RuntimeException("Avis non trouvé"));
        avis.setApproved(true);
        return avisRepository.save(avis);
    }

    public void supprimerAvis(Long avisId) {
        avisRepository.deleteById(avisId);
    }
}