package com.PFE.demo.Entity;

import jakarta.persistence.*;

@Entity
public class ProduitImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    // Relation MANY-TO-ONE avec Produit
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    // Getters & Setters
    public Long getId() { return id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
}