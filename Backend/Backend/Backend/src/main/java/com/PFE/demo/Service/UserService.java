package com.PFE.demo.Service;

import com.PFE.demo.Entity.User;
import com.PFE.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🔥 GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 🔥 GET ONLY COACHS
    public List<User> getCoachs() {
        return userRepository.findByRole_Id(2L); // 2 = COACH
    }

    // 🔥 UPDATE USER
    public User updateUser(Long id, User user) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setNom(user.getNom());
        existing.setPrenom(user.getPrenom());
        existing.setEmail(user.getEmail());
        existing.setTelephone(user.getTelephone());
        existing.setPassword(user.getPassword());
        existing.setDateNaissance(user.getDateNaissance());
        existing.setRole(user.getRole());
        existing.setGroupe(user.getGroupe());

        return userRepository.save(existing);
    }

    // 🔥 DELETE USER
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}