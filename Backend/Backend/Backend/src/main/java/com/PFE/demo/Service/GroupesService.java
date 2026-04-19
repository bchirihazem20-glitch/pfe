package com.PFE.demo.Service;


import com.PFE.demo.Dto.GroupesRequest;
import com.PFE.demo.Dto.GroupesResponse;
import com.PFE.demo.Entity.Groupes;
import com.PFE.demo.Repository.GroupesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupesService {

    private final GroupesRepository groupesRepository;

    public GroupesService(GroupesRepository groupesRepository) {
        this.groupesRepository = groupesRepository;
    }

    public GroupesResponse create(GroupesRequest request) {
        Groupes groupe = new Groupes();
        groupe.setLibelle(request.getLibelle());
        groupe.setDescription(request.getDescription());

        Groupes saved = groupesRepository.save(groupe);
        return mapToResponse(saved);
    }

    public List<GroupesResponse> getAll() {
        return groupesRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public GroupesResponse getById(Long id) {
        Groupes groupe = groupesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Groupe introuvable avec id : " + id));

        return mapToResponse(groupe);
    }

    public GroupesResponse update(Long id, GroupesRequest request) {
        Groupes groupe = groupesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Groupe introuvable avec id : " + id));

        groupe.setLibelle(request.getLibelle());
        groupe.setDescription(request.getDescription());

        Groupes updated = groupesRepository.save(groupe);
        return mapToResponse(updated);
    }

    public void delete(Long id) {
        Groupes groupe = groupesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Groupe introuvable avec id : " + id));

        groupesRepository.delete(groupe);
    }

    private GroupesResponse mapToResponse(Groupes groupe) {
        int nombreUsers = groupe.getUsers() != null ? groupe.getUsers().size() : 0;

        return new GroupesResponse(
                groupe.getId(),
                groupe.getLibelle(),
                groupe.getDescription(),
                nombreUsers
        );
    }
}