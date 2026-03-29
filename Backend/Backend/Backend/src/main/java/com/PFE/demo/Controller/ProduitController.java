package com.PFE.demo.Controller;


import com.PFE.demo.Entity.Produit;
import com.PFE.demo.Repository.ProduitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping()
    public ResponseEntity<?> createProduit(
            @RequestParam("nom") String nom,
            @RequestParam("prix") double prix,
            @RequestParam("promo") int promo,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            String folder = "uploads/";
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path path = Paths.get(folder + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, imageFile.getBytes());

            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setPrix(prix);
            produit.setPromo(promo);
            produit.setImage("/" + folder + fileName); // path اللي باش تستخدمه في frontend

            Produit savedProduit = produitRepository.save(produit);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduit);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'upload");
        }
    }

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
