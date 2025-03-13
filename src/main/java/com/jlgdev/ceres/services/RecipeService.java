package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.dataAccessObject.RecipeDAO;
import com.jlgdev.ceres.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Iterable<RecipeDAO> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<RecipeDAO> getRecipeById(String id) {
        return recipeRepository.findById(id);
    }

    public Iterable<RecipeDAO> getRecipeByTitle(String title) {
        return recipeRepository.findByTitleEn(title);
    }

    public Iterable<RecipeDAO> getRecipeByTitleContaining(String title) {
        return recipeRepository.findByTitleEnContaining(title);
    }

    public RecipeDAO save(RecipeDAO Recipe) {
        return recipeRepository.save(Recipe);
    }
}
