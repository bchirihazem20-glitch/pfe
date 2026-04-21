package com.PFE.demo.Controller;

import com.PFE.demo.Entity.User;
import com.PFE.demo.Service.EntraineurService;
import com.PFE.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/entraineur")
@CrossOrigin("*")
public class EntraineurController {

    private final EntraineurService entraineurService;

    public EntraineurController(EntraineurService entraineurService) {
        this.entraineurService = entraineurService;
    }

    // 🔥 Afficher seulement les coachs
    @GetMapping("/coachs")
    public List<User> getCoachs() {
        return entraineurService.getCoachs();
    }
}