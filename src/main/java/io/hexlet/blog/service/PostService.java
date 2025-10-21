package io.hexlet.blog.service;

import io.hexlet.blog.exception.PostNotFoundException;
import io.hexlet.blog.model.Post;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();

    // Добавление поста
    public Post create(Post post) {
        posts.add(post);
        return post;
    }

    // Получение всех постов
    public List<Post> getAll() {
        return posts;
    }

    // Получение поста по id
    public Post getById(String id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException(id));
    }
}
