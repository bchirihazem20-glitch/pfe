package com.PFE.demo.Entity;

import jakarta.persistence.*;

@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double prix;

    private String type; // ex: carte, paypal, cash

    // Relation MANY-TO-ONE avec Produit
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    // Getters & Setters
    public Long getId() { return id; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
}