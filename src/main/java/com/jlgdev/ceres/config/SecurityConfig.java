package com.jlgdev.ceres.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jlgdev.ceres.jwt.JWTAuthenticationFilter;

// import org.springframework.security.config.Customizer;

// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.provisioning.UserDetailsManager;

@Configuration // Indique à Spring que cette classe est une classe de configuration
@EnableWebSecurity // Active la sécurité web
public class SecurityConfig {

    @Bean // ici on configure les accès aux ressources (api)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // Désactive la protection CSRF = C'est pour des API et pas pour des pages
                                          // internet
                .authorizeHttpRequests(
                        authz -> authz
                                .requestMatchers(HttpMethod.GET, "/**").permitAll()
                                // .requestMatchers(HttpMethod.POST, "/profile/auth/**").permitAll()
                                // .requestMatchers(HttpMethod.GET, "/profile/user/**").hasAnyAuthority("USER","ADMIN")
                                // .requestMatchers(HttpMethod.GET, "/khaos/**").hasAuthority("ADMIN")
                                // .anyRequest().authenticated() // toutes les autres requêtes nécessient une authentification
                                .anyRequest().permitAll()
                );

        // http.httpBasic(Customizer.withDefaults()); // active l'authentification HTTP de base // Finito pipo

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // @Bean
    // public UserDetailsManager userDetailsManager() {

    // // UserDetail c'est la classe de User de Spring Scurity
    // UserDetails user = User.builder()
    // .username("user")
    // .password("{noop}user") // {noop} indique que le mot de passe n'est pas
    // chiffré
    // .roles("USER")
    // .build();

    // UserDetails admin = User.builder()
    // .username("admin")
    // .password("{noop}admin") // {noop} indique que le mot de passe n'est pas
    // chiffré
    // .roles("ADMIN", "USER")
    // .build();

    // return new InMemoryUserDetailsManager(user, admin);
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean // configuration de l' AuthenticationManager
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
