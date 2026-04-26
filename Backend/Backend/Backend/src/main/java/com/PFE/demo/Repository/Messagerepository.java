package com.academy.repository;
import com.academy.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByDestinataireIdOrderByDateEnvoiDesc(Long destinataireId);
    List<Message> findByExpediteurId(Long expediteurId);
    long countByDestinataireIdAndIsReadFalse(Long destinataireId);
}