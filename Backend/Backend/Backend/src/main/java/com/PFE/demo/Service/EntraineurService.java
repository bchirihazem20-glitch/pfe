package com.PFE.demo.Service;

import com.PFE.demo.Repository.EntraineurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntraineurService {

    @Autowired
    private EntraineurRepository repo;

    // Ajouter
    public Entraineur ajouter(Entraineur e) {
        return repo.save(e);
    }

    // Afficher tout
    public List<Entraineur> getAll() {
        return repo.findAll();
    }

    // Modifier
    public Entraineur modifier(Long id, Entraineur newE) {
        return repo.findById(id).map(e -> {
            e.setNom(newE.getNom());
            e.setPrenom(newE.getPrenom());
            e.setSpecialite(newE.getSpecialite());
            e.setEmail(newE.getEmail());
            e.setTelephone(newE.getTelephone());
            return repo.save(e);
        }).orElseThrow(() -> new RuntimeException("Entraineur non trouvé"));
    }

    // Supprimer
    public void supprimer(Long id) {
        repo.deleteById(id);
    }
}