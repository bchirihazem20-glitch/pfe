package com.PFE.demo.Controller;

import com.PFE.demo.Entity.Groups;
import com.PFE.demo.Entity.GroupJoueurs;
import com.PFE.demo.Entity.User;
import com.PFE.demo.Repository.GroupJoueursRepository;
import com.PFE.demo.Repository.GroupsRepository;
import com.PFE.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin("*")
public class GroupsController {

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private GroupJoueursRepository groupJoueursRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Créer groupe avec coach
    @PostMapping
    public Groups createGroup(@RequestParam String libelle,
                              @RequestParam Long coachId) {

        User coach = userRepository.findById(coachId).orElseThrow();

        Groups group = new Groups();
        group.setLibelle(libelle);
        group.setCoach(coach);

        return groupsRepository.save(group);
    }

    // ✅ Tous les groupes avec coach
    @GetMapping
    public List<?> getAllGroups() {

        List<Groups> list = groupsRepository.findAll();

        return list.stream().map(g -> Map.of(
                "id", g.getId(),
                "libelle", g.getLibelle(),
                "coach_nom", g.getCoach().getNom()
        )).toList();
    }

    // ✅ Groupe par ID
    @GetMapping("/{id}")
    public Object getGroupById(@PathVariable Long id) {

        Groups g = groupsRepository.findById(id).orElseThrow();

        return Map.of(
                "id", g.getId(),
                "libelle", g.getLibelle(),
                "coach_nom", g.getCoach().getNom()
        );
    }

    // ✅ Ajouter joueur
    @PostMapping("/{groupId}/add-joueur/{joueurId}")
    public GroupJoueurs addJoueur(@PathVariable Long groupId,
                                  @PathVariable Long joueurId) {

        Groups group = groupsRepository.findById(groupId).orElseThrow();
        User joueur = userRepository.findById(joueurId).orElseThrow();

        GroupJoueurs gj = new GroupJoueurs();
        gj.setGroup(group);
        gj.setJoueur(joueur);

        return groupJoueursRepository.save(gj);
    }

    // ✅ Liste joueurs avec nom + prénom
    @GetMapping("/{groupId}/joueurs")
    public List<?> getJoueurs(@PathVariable Long groupId) {

        List<GroupJoueurs> list = groupJoueursRepository.findByGroupId(groupId);

        return list.stream().map(gj -> Map.of(
                "id", gj.getId(),
                "nom", gj.getJoueur().getNom()
        )).toList();
    }

    // ✅ Supprimer joueur
    @DeleteMapping("/{groupId}/remove-joueur/{joueurId}")
    public void removeJoueur(@PathVariable Long groupId,
                             @PathVariable Long joueurId) {

        groupJoueursRepository.deleteByGroupIdAndJoueurId(groupId, joueurId);
    }
}