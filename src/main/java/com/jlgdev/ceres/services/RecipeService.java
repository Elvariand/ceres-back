package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.mongo.Recipe;
import com.jlgdev.ceres.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Iterable<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(String id) {
        return recipeRepository.findById(id);
    }

    public Iterable<Recipe> getRecipeByTitle(String title) {
        return recipeRepository.findByTitle(title);
    }

    public Iterable<Recipe> getRecipeByTitleContaining(String title) {
        return recipeRepository.findByTitleContaining(title);
    }

    public Recipe save(Recipe Recipe) {
        return recipeRepository.save(Recipe);
    }
}
