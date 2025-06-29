package com.jlgdev.ceres.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlgdev.ceres.models.dataTransferObject.NoSQL.RecipeDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.UserEntityCreateDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.UserEntityReadDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.UserEntityUpdateDTO;
import com.jlgdev.ceres.services.RecipeService;
import com.jlgdev.ceres.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/constellation")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/new")
    public ResponseEntity<UserEntityReadDTO> createUser(@RequestBody @Valid UserEntityCreateDTO userCreateDTO) {
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntityReadDTO> readUser(@PathVariable Long id) {
        Optional<UserEntityReadDTO> user = userService.readUser(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserEntityReadDTO> readUser(@PathVariable String email) {
        Optional<UserEntityReadDTO> user = userService.readUser(email);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
            @RequestBody @Valid UserEntityUpdateDTO userUpdateDTO) {
        userService.updateUser(id, userUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{email}/addFavorite")
    public ResponseEntity<Void> addFavorite(@PathVariable String email, @RequestParam String recipeId) {
        userService.addFavorite(email, recipeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{email}/getFavorites")
    public ResponseEntity<Set<String>> getFavorite(@PathVariable String email) {
        UserEntityReadDTO dto = userService.readUser(email).get();
        return ResponseEntity.ok(dto.getFavorite());
    }

    @GetMapping("/{email}/getFavoriteRecipes")
    public ResponseEntity<Set<RecipeDTO>> getFavoriteRecipes(@PathVariable String email) {
        UserEntityReadDTO dto = userService.readUser(email).get();
        Set<String> recipesIds = dto.getFavorite();
        Set<RecipeDTO> recipes = new HashSet<>();
        for (String id : recipesIds) {
            recipes.add(recipeService.getRecipeById(id).get());
        }

        return ResponseEntity.ok(recipes);
    }

    @DeleteMapping("/{email}/removeFavorite")
    public ResponseEntity<Void> removeFavorite(@PathVariable String email, @RequestParam String recipeId) {
        userService.removeFavorite(email, recipeId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{email}/addHistory")
    public ResponseEntity<Void> addHistory(@PathVariable String email, @RequestParam String recipeId) {
        userService.addHistory(email, recipeId);
        return ResponseEntity.ok().build();
    }

    
    @GetMapping("/{email}/getHistoryRecipes")
    public ResponseEntity<List<RecipeDTO>> getHistoryRecipes(@PathVariable String email) {
        UserEntityReadDTO dto = userService.readUser(email).get();
        List<String> recipesIds = dto.getHistory();
        List<RecipeDTO> recipes = new ArrayList<>();
        for (String id : recipesIds) {
            recipes.add(recipeService.getRecipeById(id).get());
        }

        return ResponseEntity.ok(recipes);
    }
}
