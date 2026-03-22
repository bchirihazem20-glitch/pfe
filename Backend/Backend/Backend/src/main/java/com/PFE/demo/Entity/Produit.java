package com.PFE.demo.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private double prix;

    private double promo;

    // Relation avec ImageProduit
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<ProduitImage> images;

    // Relation avec Paiement
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<Paiement> paiements;

    // Getters & Setters
    public Long getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public double getPromo() { return promo; }
    public void setPromo(double promo) { this.promo = promo; }

    public List<ProduitImage> getImages() { return images; }
    public void setImages(List<ProduitImage> images) { this.images = images; }

    public List<Paiement> getPaiements() { return paiements; }
    public void setPaiements(List<Paiement> paiements) { this.paiements = paiements; }
}