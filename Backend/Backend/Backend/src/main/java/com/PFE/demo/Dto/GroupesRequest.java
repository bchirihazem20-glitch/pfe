package com.PFE.demo.Dto;

public class GroupRequest {

    private String libelle;
    private Long coach;
    private int numberOfGroups;


    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getCoach() {
        return coach;
    }

    public void setCoach(Long coach) {
        this.coach = coach;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }
}