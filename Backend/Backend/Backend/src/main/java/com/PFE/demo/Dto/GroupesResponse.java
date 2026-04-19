package com.PFE.demo.Dto;

public class GroupesResponse {

    private Long id;
    private String libelle;
    private String description;
    private int nombreUsers;

    public GroupesResponse() {
    }

    public GroupesResponse(Long id, String libelle, String description, int nombreUsers) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.nombreUsers = nombreUsers;
    }

    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDescription() {
        return description;
    }

    public int getNombreUsers() {
        return nombreUsers;
    }
}