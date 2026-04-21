package com.PFE.demo.Controller;

import com.PFE.demo.Entity.User;
import com.PFE.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class EntraineurController {

    private final UserService userService;

    public EntraineurController(UserService userService) {
        this.userService = userService;
    }

    // 🔥 Afficher seulement les coachs
    @GetMapping("/coachs")
    public List<User> getCoachs() {
        return userService.getCoachs();
    }
}