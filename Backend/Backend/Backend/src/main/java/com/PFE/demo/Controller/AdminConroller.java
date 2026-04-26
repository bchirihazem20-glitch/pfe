package com.academy.controller;

import com.academy.entity.*;
import com.academy.repository.*;
import com.academy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private JoueurRepository joueurRepository;
    @Autowired private EntraineurRepository entraineurRepository;
    @Autowired private GroupeRepository groupeRepository;
    @Autowired private PaiementRepository paiementRepository;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard() {
        return ResponseEntity.ok(adminService.getDashboardStats());
    }

    // ===== JOUEURS =====
    @GetMapping("/joueurs")
    public ResponseEntity<List<Joueur>> getAllJoueurs() {
        return ResponseEntity.ok(joueurRepository.findAll());
    }

    @GetMapping("/joueurs/en-attente")
    public ResponseEntity<List<Joueur>> getJoueursEnAttente() {
        return ResponseEntity.ok(adminService.getJoueursEnAttente());
    }

    @PutMapping("/joueurs/{id}/accepter")
    public ResponseEntity<?> accepterJoueur(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.accepterJoueur(id));
    }

    @PutMapping("/joueurs/{id}/refuser")
    public ResponseEntity<?> refuserJoueur(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.refuserJoueur(id));
    }

    @PutMapping("/joueurs/{joueurId}/affecter-groupe/{groupeId}")
    public ResponseEntity<?> affecterGroupe(@PathVariable Long joueurId, @PathVariable Long groupeId) {
        return ResponseEntity.ok(adminService.affecterJoueurGroupe(joueurId, groupeId));
    }

    // ===== COACHS =====
    @GetMapping("/coachs")
    public ResponseEntity<List<Entraineur>> getAllCoachs() {
        return ResponseEntity.ok(entraineurRepository.findAll());
    }

    @PostMapping("/coachs")
    public ResponseEntity<?> creerCoach(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(adminService.creerCoach(
                body.get("nom"), body.get("prenom"),
                body.get("email"), body.get("grade")
        ));
    }

    @DeleteMapping("/coachs/{utilisateurId}")
    public ResponseEntity<?> supprimerCoach(@PathVariable Long utilisateurId) {
        adminService.supprimerCoach(utilisateurId);
        return ResponseEntity.ok(Map.of("message", "Coach supprimé"));
    }

    // ===== GROUPES =====
    @GetMapping("/groupes")
    public ResponseEntity<List<Groupe>> getAllGroupes() {
        return ResponseEntity.ok(groupeRepository.findAll());
    }

    @PostMapping("/groupes")
    public ResponseEntity<?> creerGroupe(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(adminService.creerGroupe(
                body.get("nom"), body.get("categorie"), body.get("saison")
        ));
    }

    // ===== PAIEMENTS =====
    @GetMapping("/paiements")
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        return ResponseEntity.ok(paiementRepository.findAll());
    }

    @PutMapping("/paiements/{id}/valider")
    public ResponseEntity<?> validerPaiement(@PathVariable Long id) {
        Paiement p = paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé"));
        p.setStatut(Paiement.StatutPaiement.PAYE);
        return ResponseEntity.ok(paiementRepository.save(p));
    }

    // ===== AVIS =====
    @GetMapping("/avis")
    public ResponseEntity<List<Avis>> getAvisEnAttente() {
        return ResponseEntity.ok(adminService.getAvisEnAttente());
    }

    @PutMapping("/avis/{id}/approuver")
    public ResponseEntity<?> approuverAvis(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.approuverAvis(id));
    }

    @DeleteMapping("/avis/{id}")
    public ResponseEntity<?> supprimerAvis(@PathVariable Long id) {
        adminService.supprimerAvis(id);
        return ResponseEntity.ok(Map.of("message", "Avis supprimé"));
    }
}