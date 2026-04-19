package com.PFE.demo.Controller;

import com.PFE.demo.Entity.User;
import com.PFE.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // pour Angular
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🔥 GET users by groupeId
    @GetMapping("/groupe/{groupeId}")
    public List<User> getUsersByGroupe(@PathVariable Long groupeId) {
        return userService.getUsersByGroupeId(groupeId);
    }
}