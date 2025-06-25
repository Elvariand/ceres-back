package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.dataTransferObject.NoSQL.RecipeDTO;
import com.jlgdev.ceres.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Iterable<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<RecipeDTO> getRecipeById(String id) {
        return recipeRepository.findById(id);
    }

    public Iterable<RecipeDTO> getRecipeByTitle(String title) {
        return recipeRepository.findByTitleEn(title);
    }

    public Iterable<RecipeDTO> getRecipeByTitleContaining(String title) {
        return recipeRepository.findByTitleEnContaining(title);
    }

    public RecipeDTO save(RecipeDTO Recipe) {
        return recipeRepository.save(Recipe);
    }
}
