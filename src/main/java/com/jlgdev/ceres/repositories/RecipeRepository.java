package com.jlgdev.ceres.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import com.jlgdev.ceres.models.dataTransferObject.RecipeDTO;

public interface RecipeRepository extends MongoRepository<RecipeDTO, String>{

    @SuppressWarnings("null")
    Optional<RecipeDTO> findById(@NonNull String id);
    List<RecipeDTO> findByTitleEn(String title);
    List<RecipeDTO> findByTitleEnContaining(String title);
}
