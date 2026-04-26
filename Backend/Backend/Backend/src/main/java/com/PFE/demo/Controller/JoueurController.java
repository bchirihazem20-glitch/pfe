package com.academy.controller;

import com.academy.entity.*;
import com.academy.service.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/joueur")
@PreAuthorize("hasAnyRole('JOUEUR','ADMIN')")
@CrossOrigin(origins = "*")
public class JoueurController {

    @Autowired private JoueurService joueurService;

    @GetMapping("/dashboard/{utilisateurId}")
    public ResponseEntity<?> getDashboard(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(joueurService.getDashboardStats(utilisateurId));
    }

    @GetMapping("/profil/{utilisateurId}")
    public ResponseEntity<?> getProfil(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(joueurService.getMonProfil(utilisateurId));
    }

    @GetMapping("/evaluations/{utilisateurId}")
    public ResponseEntity<List<Evaluation>> getMesEvaluations(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(joueurService.getMesEvaluations(utilisateurId));
    }

    @GetMapping("/paiements/{utilisateurId}")
    public ResponseEntity<List<Paiement>> getMesPaiements(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(joueurService.getMesPaiements(utilisateurId));
    }

    @PostMapping("/paiements/{utilisateurId}")
    public ResponseEntity<?> effectuerPaiement(
            @PathVariable Long utilisateurId,
            @RequestBody Map<String, Object> body) {
        Paiement p = joueurService.effectuerPaiement(
                utilisateurId,
                Double.parseDouble(body.get("montant").toString()),
                (String) body.get("moisConcerne")
        );
        return ResponseEntity.ok(p);
    }

    @PostMapping("/avis/{utilisateurId}")
    public ResponseEntity<?> ajouterAvis(
            @PathVariable Long utilisateurId,
            @RequestBody Map<String, Object> body) {
        Avis avis = joueurService.ajouterAvis(
                utilisateurId,
                (String) body.get("avis"),
                (Integer) body.get("note")
        );
        return ResponseEntity.ok(avis);
    }

    @GetMapping("/seances-test")
    public ResponseEntity<List<SeanceTest>> getSeancesDisponibles(
            @RequestParam(required = false) String categorieAge) {
        return ResponseEntity.ok(joueurService.getSeancesTestDisponibles(categorieAge));
    }
}