package com.PFE.demo.Controller;


import com.PFE.demo.Dto.GroupesRequest;
import com.PFE.demo.Dto.GroupesResponse;
import com.PFE.demo.Entity.User;
import com.PFE.demo.Repository.UserRepository;
import com.PFE.demo.Service.GroupesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groupes")
@CrossOrigin("*")
public class GroupesController {

    private final GroupesService groupesService;
    private final UserRepository userRepository;

    public GroupesController(GroupesService groupesService, UserRepository userRepository) {
        this.groupesService = groupesService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<GroupesResponse> create(@RequestBody GroupesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupesService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<GroupesResponse>> getAll() {
        return ResponseEntity.ok(groupesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupesResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(groupesService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupesResponse> update(@PathVariable Long id,
                                                  @RequestBody GroupesRequest request) {
        return ResponseEntity.ok(groupesService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        groupesService.delete(id);
        return ResponseEntity.ok("Groupe supprimé avec succès");
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> getUsersByGroupe(@PathVariable Long id) {
        return ResponseEntity.ok(userRepository.findByGroupe_Id(id));
    }
}