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

    public List<User> getUsersByGroupeId(Long groupeId) {
        return userRepository.findByGroupe_Id(groupeId);
    }
}