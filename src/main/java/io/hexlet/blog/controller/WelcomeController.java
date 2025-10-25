package io.hexlet.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {
    @Value("${app.welcome-message}")
    private String welcomeMessage;

    @GetMapping("/")
    public String home() {
        return welcomeMessage;
    }

    @GetMapping("/about")
    public String about() {
        return "This is simple Spring blog!";
    }

}
