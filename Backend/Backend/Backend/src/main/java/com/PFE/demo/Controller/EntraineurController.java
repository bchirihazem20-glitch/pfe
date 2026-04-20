package com.PFE.demo.Controller;

import com.PFE.demo.Service.EntraineurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entraineurs")
@CrossOrigin("*")
public class EntraineurController {

    @Autowired
    private EntraineurService service;

    // Ajouter
    @PostMapping
    public Entraineur ajouter(@RequestBody Entraineur e) {
        return service.ajouter(e);
    }

    // Afficher
    @GetMapping
    public List<Entraineur> getAll() {
        return service.getAll();
    }

    // Modifier
    @PutMapping("/{id}")
    public Entraineur modifier(@PathVariable Long id, @RequestBody Entraineur e) {
        return service.modifier(id, e);
    }

    // Supprimer
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }
}