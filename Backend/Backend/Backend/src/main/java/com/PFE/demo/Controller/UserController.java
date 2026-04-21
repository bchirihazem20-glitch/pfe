package com.PFE.demo.Controller;

import com.PFE.demo.Entity.User;
import com.PFE.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🔥 GET ALL USERS
    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    // 🔥 GET ONLY COACHS
    @GetMapping("/coachs")
    public List<User> getCoachs() {
        return userService.getCoachs();
    }

    // 🔥 UPDATE USER
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // 🔥 DELETE USER
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}