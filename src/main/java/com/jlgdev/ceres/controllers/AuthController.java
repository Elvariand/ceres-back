package com.jlgdev.ceres.controllers;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlgdev.ceres.jwt.JWTGenerator;
import com.jlgdev.ceres.models.dataTransferObject.NoSQL.AuthResponseDTO;
import com.jlgdev.ceres.models.dataTransferObject.NoSQL.LoginDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.RegisterDTO;
import com.jlgdev.ceres.models.entities.Role;
import com.jlgdev.ceres.models.entities.UserEntity;
import com.jlgdev.ceres.repositories.RoleRepository;
import com.jlgdev.ceres.repositories.UserRepository;

@RestController
@RequestMapping("/pantheon")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {

    // On peut annoter les attributs avec @Autowired et ne pas mettre final mais ce
    // n'est pas recommandé notamment pour les tests unitaires

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JWTGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository,
            JWTGenerator jwtGenerator, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtGenerator = jwtGenerator;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            return new ResponseEntity<>("Un compte avec cet email existe déjà!", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("Utilisateur enregistré!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        System.out.println("Je suis ici");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        System.out.println(authentication.toString());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

}
