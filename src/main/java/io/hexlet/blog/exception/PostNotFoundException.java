package io.hexlet.blog.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String id) {
        super("Пост с id=" + id + " не найден");
    }
}
