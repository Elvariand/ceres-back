package com.jlgdev.ceres.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import com.jlgdev.ceres.models.dataAccessObject.RecipeDAO;

public interface RecipeRepository extends MongoRepository<RecipeDAO, String>{

    @SuppressWarnings("null")
    Optional<RecipeDAO> findById(@NonNull String id);
    List<RecipeDAO> findByTitleEn(String title);
    List<RecipeDAO> findByTitleEnContaining(String title);
}
