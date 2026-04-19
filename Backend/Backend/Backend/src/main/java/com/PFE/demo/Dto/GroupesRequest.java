package com.PFE.demo.Dto;

public class GroupesRequest {

    private String libelle;
    private String description;

    public GroupesRequest() {
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}