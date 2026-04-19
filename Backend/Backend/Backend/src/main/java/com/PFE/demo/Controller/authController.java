package com.PFE.demo.Controller;

import com.PFE.demo.Entity.Role;
import com.PFE.demo.Entity.User;
import com.PFE.demo.Repository.RoleRepository;
import com.PFE.demo.Repository.UserRepository;
import com.PFE.demo.Security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class authController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public authController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public Object register(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email already exists!"));
        }

        Role role = roleRepository.findByName("JOUEUR");
        if (role == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Role JOUEUR not found"));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "User registered successfully!"));
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        User foundAccount = userRepository.findByEmail(user.getEmail());

        if (foundAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "User not found"));
        }

        boolean matches = passwordEncoder.matches(user.getPassword(), foundAccount.getPassword());
        if (!matches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Password incorrect"));
        }

        String token = jwtService.generateToken(foundAccount.getEmail());

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of(
                        "token", token,
                        "message", "Login successful"
                ));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        System.out.println("EMAIL FROM CONTEXT: " + email);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "User not found"));
        }

        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "nom", user.getNom(),
                "email", user.getEmail(),
                "role", user.getRole().getName(),
                "dateNaissance", user.getDateNaissance()
        ));
    }
}