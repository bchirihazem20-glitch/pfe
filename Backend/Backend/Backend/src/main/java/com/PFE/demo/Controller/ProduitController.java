package com.PFE.demo.Controller;


import com.PFE.demo.Entity.Produit;
import com.PFE.demo.Repository.ProduitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "*")
public class ProduitController {

    private final ProduitRepository produitRepository;

    public ProduitController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    // ✅ GET ALL PRODUITS
    @GetMapping
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // ✅ GET PRODUIT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduitById(@PathVariable Long id) {
        Optional<Produit> produit = produitRepository.findById(id);

        if (produit.isPresent()) {
            return ResponseEntity.ok(produit.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produit non trouvé");
        }
    }

    // ✅ CREATE PRODUIT
    @PostMapping
    public ResponseEntity<?> createProduit(@RequestBody Produit produit) {
        Produit savedProduit = produitRepository.save(produit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduit);
    }

    // ✅ UPDATE PRODUIT
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduit(@PathVariable Long id, @RequestBody Produit newProduit) {

        Optional<Produit> existingProduit = produitRepository.findById(id);

        if (existingProduit.isPresent()) {
            Produit produit = existingProduit.get();

            produit.setNom(newProduit.getNom());
            produit.setPrix(newProduit.getPrix());
            produit.setPromo(newProduit.getPromo());

            produitRepository.save(produit);

            return ResponseEntity.ok(produit);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produit non trouvé");
        }
    }

    // ✅ DELETE PRODUIT
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduit(@PathVariable Long id) {

        if (!produitRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produit non trouvé");
        }

        produitRepository.deleteById(id);

        return ResponseEntity.ok("Produit supprimé avec succès");
    }
}
