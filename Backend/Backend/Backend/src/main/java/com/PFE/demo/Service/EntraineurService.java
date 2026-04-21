package com.PFE.demo.Service;

import com.PFE.demo.Entity.User;
import com.PFE.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EntraineurService {

    @Autowired
    private UserRepository repo;

    // Ajouter
    public User ajouter(User e) {
        return repo.save(e);
    }

    // Afficher tout
    public List<User> getAll() {
        return repo.findAll();
    }

    // Modifier
    public User modifier(Long id, User newE) {
        return repo.findById(id).map(e -> {
            e.setNom(newE.getNom());
            e.setPrenom(newE.getPrenom());
            e.setEmail(newE.getEmail());
            return repo.save(e);
        }).orElseThrow(() -> new RuntimeException("Entraineur non trouvé"));
    }
    public List<User> getCoachs() {
        return repo.findByRole("COACH");
    }

    // Supprimer
    public void supprimer(Long id) {
        repo.deleteById(id);
    }
}