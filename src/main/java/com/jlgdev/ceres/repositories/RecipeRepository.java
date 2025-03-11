package com.jlgdev.ceres.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import com.jlgdev.ceres.models.mongo.Recipe;

public interface RecipeRepository extends MongoRepository<Recipe, String>{

    @SuppressWarnings("null")
    Optional<Recipe> findById(@NonNull String id);
    List<Recipe> findByTitle(String title);
    List<Recipe> findByTitleContaining(String title);
}
