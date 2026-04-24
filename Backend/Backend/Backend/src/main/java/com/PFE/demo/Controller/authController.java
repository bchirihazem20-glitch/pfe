package com.PFE.demo.Controller;

import com.PFE.demo.Dto.RegisterRequest;
import com.PFE.demo.Entity.Groupes;
import com.PFE.demo.Entity.Role;
import com.PFE.demo.Entity.User;
import com.PFE.demo.Repository.GroupesRepository;
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
    private final GroupesRepository groupesRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public authController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          GroupesRepository groupesRepository,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.groupesRepository = groupesRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public Object register(@RequestBody RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email already exists!"));
        }

        Role role = roleRepository.findByName("JOUEUR");
        if (role == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Role JOUEUR not found"));
        }

        Groupes groupe = null;

        if (request.getGroupId() != null) {
            groupe = groupesRepository.findById(request.getGroupId())
                    .orElse(null);

            if (groupe == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Groupe not found"));
            }
        }

        User user = new User();
        user.setNom(request.getNom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setDateNaissance(request.getDateNaissance());
        user.setGroupe(groupe);

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
                "dateNaissance", user.getDateNaissance(),
                "groupId", user.getGroupe() != null ? user.getGroupe().getId() : null
        ));
    }
}