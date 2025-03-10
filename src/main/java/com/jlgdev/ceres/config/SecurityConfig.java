package com.jlgdev.ceres.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean // ici on configure les accès aux ressources (api)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // Désactive la protection CSRF = C'est pour des API et pas pour des pages internet
                .authorizeHttpRequests(
                        authz -> authz
                                // autorise les requêtes GET sur /api/public/**
                                .requestMatchers(HttpMethod.GET, "/**").permitAll()
                                // autorise les requêtes POST sur /api/auth/**
                                .requestMatchers(HttpMethod.POST, "/**").permitAll()
                );
        return http.build();
    }

}
