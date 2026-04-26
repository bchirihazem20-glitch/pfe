package com.academy.controller;

import com.academy.entity.Coach;
import com.academy.service.CoachService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
@CrossOrigin("*")
public class CoachController {

    private final CoachService service;

    public CoachController(CoachService service) {
        this.service = service;
    }

    // ➕ add
    @PostMapping
    public Coach add(@RequestBody Coach c) {
        return service.add(c);
    }

    // 📋 all
    @GetMapping
    public List<Coach> getAll() {
        return service.getAll();
    }

    // 🔍 by id
    @GetMapping("/{id}")
    public Coach getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ❌ delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}