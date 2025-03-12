package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.jsonToObject.RecipeJTO;
import com.jlgdev.ceres.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Iterable<RecipeJTO> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<RecipeJTO> getRecipeById(String id) {
        return recipeRepository.findById(id);
    }

    public Iterable<RecipeJTO> getRecipeByTitle(String title) {
        return recipeRepository.findByTitle(title);
    }

    public Iterable<RecipeJTO> getRecipeByTitleContaining(String title) {
        return recipeRepository.findByTitleContaining(title);
    }

    public RecipeJTO save(RecipeJTO Recipe) {
        return recipeRepository.save(Recipe);
    }
}
