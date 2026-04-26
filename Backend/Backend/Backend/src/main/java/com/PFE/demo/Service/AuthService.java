package com.academy.service;

import com.academy.dto.AuthDto;
import com.academy.entity.Joueur;
import com.academy.entity.Utilisateur;
import com.academy.repository.JoueurRepository;
import com.academy.repository.UtilisateurRepository;
import com.academy.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JoueurRepository joueurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthDto.AuthResponse login(AuthDto.LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Utilisateur user = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return new AuthDto.AuthResponse(jwt, user.getId(), user.getNom(), user.getPrenom(),
                user.getEmail(), user.getRole().name());
    }

    public Utilisateur register(AuthDto.RegisterRequest request) {
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        // Validate age > 4 years
        LocalDate dateNaissance = request.getDateNaissance();
        int age = Period.between(dateNaissance, LocalDate.now()).getYears();
        if (age <= 4) {
            throw new RuntimeException("L'âge doit être supérieur à 4 ans");
        }

        Utilisateur user = new Utilisateur();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        user.setTelephone(request.getTelephone());
        user.setAdresse(request.getAdresse());
        user.setDateNaissance(dateNaissance);
        user.setNomPere(request.getNomPere());
        user.setTelephonePere(request.getTelephonePere());
        user.setRole(Utilisateur.Role.JOUEUR);
        user.setActive(false);

        Utilisateur savedUser = utilisateurRepository.save(user);

        // Create Joueur profile
        Joueur joueur = new Joueur();
        joueur.setUtilisateur(savedUser);
        joueur.setCategorieAge(getCategorieByAge(age));
        joueur.setStatutInscription(Joueur.StatutInscription.EN_ATTENTE);
        joueurRepository.save(joueur);

        return savedUser;
    }

    private String getCategorieByAge(int age) {
        if (age <= 6) return "U6";
        if (age <= 8) return "U8";
        if (age <= 10) return "U10";
        if (age <= 12) return "U12";
        if (age <= 14) return "U14";
        if (age <= 16) return "U16";
        if (age <= 18) return "U18";
        return "Senior";
    }
}