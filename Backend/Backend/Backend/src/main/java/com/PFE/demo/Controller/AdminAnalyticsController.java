package com.PFE.demo.Controller;

import com.PFE.demo.Repository.ProduitRepository;
import com.PFE.demo.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/analytics")
@CrossOrigin(origins = "*")
public class AdminAnalyticsController {

    private final UserRepository userRepository;
    private final ProduitRepository produitRepository;

    public AdminAnalyticsController(UserRepository userRepository, ProduitRepository produitRepository) {
        this.userRepository = userRepository;
        this.produitRepository = produitRepository;
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> summary() {
        long totalUsers = userRepository.count();
        long joueurs = userRepository.countByRole("JOUEUR");
        long coachs = userRepository.countByRole("COACH");
        long admins = userRepository.countByRole("ADMIN");
        long autres = Math.max(0, totalUsers - joueurs - coachs - admins);
        long totalProduits = produitRepository.count();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("totalUsers", totalUsers);
        body.put("joueurs", joueurs);
        body.put("coachs", coachs);
        body.put("admins", admins);
        body.put("autres", autres);
        body.put("totalProduits", totalProduits);

        return ResponseEntity.ok(body);
    }
}
