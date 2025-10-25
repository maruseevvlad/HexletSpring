package io.hexlet.blog.controller;

import io.hexlet.blog.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

// Кастомное исключение
class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String id) {
        super("Пост с id=" + id + " не найден");
    }
}

// Глобальный обработчик для 404
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handlePostNotFound(PostNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}

@RestController
@RequestMapping("/api")
public class PostsController {
    private final List<Post> posts = new ArrayList<>();

    @PostMapping("/post")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(201).body(post);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        Post post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException(id));
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(posts.stream().limit(limit).toList());
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post) {
        var maybePost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (maybePost.isPresent()) {
            var poost = maybePost.get();
            poost.setId(post.getId());
            poost.setTitle(post.getTitle());
            poost.setContent(post.getContent());
            poost.setAuthor(post.getAuthor());
        }
        return ResponseEntity.ok(post);
    }
}
