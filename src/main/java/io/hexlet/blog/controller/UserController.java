package io.hexlet.blog.controller;

import io.hexlet.blog.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public ResponseEntity<List<User>> index(@RequestParam(defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(users.stream().limit(limit).toList());
    }

    @PostMapping("/user")
    public ResponseEntity<User> create(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            System.out.println("Email is null");
            return ResponseEntity.badRequest().build();
        }
        users.add(user);
        return ResponseEntity.status(201).body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        users.removeIf(u -> u.getId().equals(id));
        return ResponseEntity.noContent().build();
    }
}
