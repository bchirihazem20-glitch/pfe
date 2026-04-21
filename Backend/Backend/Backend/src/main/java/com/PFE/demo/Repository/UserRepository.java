package com.PFE.demo.Repository;

import com.PFE.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    long countByRole_Name(String name);

    List<User> findByGroupe_Id(Long groupeId);


    List<User> findByRole_Id(Long roleId);
}