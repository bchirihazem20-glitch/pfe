package com.PFE.demo.Repository;

import com.PFE.demo.Entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Groups, Long> {
}