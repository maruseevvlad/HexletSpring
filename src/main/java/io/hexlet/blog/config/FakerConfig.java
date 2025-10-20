package io.hexlet.blog.config;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakerConfig {

    @Bean
    public Faker faker() {
        return new Faker(); // бин создаётся, даже если ты его нигде пока не используешь
    }
}
