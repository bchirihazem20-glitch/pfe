package com.PFE.demo.Controller;

import com.PFE.demo.Dto.AutoGroupRequest;
import com.PFE.demo.Entity.Enum.AgeCategory;
import com.PFE.demo.Entity.Groups;
import com.PFE.demo.Entity.GroupJoueurs;
import com.PFE.demo.Entity.User;
import com.PFE.demo.Repository.GroupJoueursRepository;
import com.PFE.demo.Repository.GroupsRepository;
import com.PFE.demo.Repository.UserRepository;
import  com.PFE.demo.Dto.GroupRequest;

import com.PFE.demo.Service.AgeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin("*")
public class GroupsController {

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private GroupJoueursRepository groupJoueursRepository;

    @Autowired
    private AgeService ageService;

    @Autowired
    private UserRepository userRepository;



    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody GroupRequest req) {

        Optional<User> coachOpt = userRepository.findById(req.getCoach());

        if (coachOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Coach not found with id: " + req.getCoach());
        }

        Groups group = new Groups();
        group.setLibelle(req.getLibelle());
        group.setCoach(coachOpt.get());

        groupsRepository.save(group);

        return ResponseEntity.ok(group);
    }

    @GetMapping
    public List<?> getAllGroups() {

        List<Groups> list = groupsRepository.findAll();

        return list.stream().map(g -> Map.of(
                "id", g.getId(),
                "libelle", g.getLibelle(),
                "coach_nom", g.getCoach().getNom(),
                "coach_id", g.getCoach().getId()
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
    public ResponseEntity<?> addJoueur(@PathVariable Long groupId,
                                       @PathVariable Long joueurId) {

        Optional<Groups> groupOpt = groupsRepository.findById(groupId);
        Optional<User> joueurOpt = userRepository.findById(joueurId);

        if (groupOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Group not found");
        }

        if (joueurOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Joueur not found");
        }

        GroupJoueurs gj = new GroupJoueurs();
        gj.setGroup(groupOpt.get());
        gj.setJoueur(joueurOpt.get());

        groupJoueursRepository.save(gj);

        return ResponseEntity.ok("Joueur ajouté avec succès");
    }

    // ✅ Liste joueurs avec nom + prénom
    @GetMapping("/{groupId}/joueurs")
    public ResponseEntity<?> getJoueurs(@PathVariable Long groupId) {

        Optional<Groups> groupOpt = groupsRepository.findById(groupId);

        if (groupOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Group not found");
        }

        Groups group = groupOpt.get();

        List<GroupJoueurs> list = groupJoueursRepository.findByGroupId(groupId);

        List<Map<String, Object>> joueurs = list.stream()
                .map(gj -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("id", gj.getJoueur().getId());
                    m.put("nom", gj.getJoueur().getNom());
                    return m;
                })
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("group_id", group.getId());
        response.put("libelle", group.getLibelle());
        response.put("joueurs", joueurs);

        return ResponseEntity.ok(response);
    }

    // ✅ Supprimer joueur
    @DeleteMapping("/{groupId}/remove-joueur/{joueurId}")
    public void removeJoueur(@PathVariable Long groupId,
                             @PathVariable Long joueurId) {

        groupJoueursRepository.deleteByGroupIdAndJoueurId(groupId, joueurId);
    }

    @PostMapping("/auto-group-by-age")
    @Transactional
    public ResponseEntity<?> autoGroupByAge(@RequestBody AutoGroupRequest req) {

        int maxSize = 15;
        User coach = userRepository.findById(req.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        List<User> players = userRepository.findAll()
              .stream()
                .filter(u -> u.getDateNaissance() != null)
                .filter(u -> groupJoueursRepository.findByJoueurId(u.getId()).isEmpty())
                .filter(u ->
                    ageService.getCategory(u.getDateNaissance())
                        .name()
                        .equals(req.getAge())
                ).toList();

        if (players.isEmpty()) {
            return ResponseEntity.badRequest().body("No available players for this age category");
        }

        List<Groups> createdGroups = new ArrayList<>();

        int index = 0;
        int groupIndex = 1;

        // 3. Create groups (ONLY for selected age)
        while (index < players.size() && groupIndex <= req.getNumberOfGroups()) {

            List<User> batch = players.subList(
                    index,
                    Math.min(index + maxSize, players.size())
            );

            // CREATE GROUP
            Groups group = new Groups();
            group.setLibelle(req.getGroupName() + "-" + groupIndex);
            group.setCoach(coach);

            groupsRepository.save(group);
            createdGroups.add(group);

            // ASSIGN PLAYERS
            for (User u : batch) {

                GroupJoueurs gj = new GroupJoueurs();
                gj.setGroup(group);
                gj.setJoueur(u);

                groupJoueursRepository.save(gj);
            }

            index += maxSize;
            groupIndex++;
        }

        return ResponseEntity.ok(Map.of(
                "message", "Auto grouping completed",
                "age", req.getAge(),
                "totalGroups", createdGroups.size()
        ));
    }
}