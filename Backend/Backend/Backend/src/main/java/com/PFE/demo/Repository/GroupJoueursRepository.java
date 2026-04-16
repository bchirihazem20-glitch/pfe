package com.PFE.demo.Repository;


import com.PFE.demo.Entity.GroupJoueurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupJoueursRepository extends JpaRepository<GroupJoueurs, Long> {

    List<GroupJoueurs> findByGroupId(Long groupId);
    List<GroupJoueurs> findByJoueurId(Long joueurId);

    void deleteByGroupIdAndJoueurId(Long groupId, Long joueurId);
}