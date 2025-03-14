package com.jlgdev.ceres.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import com.jlgdev.ceres.models.dataAccessObject.MissingIngredients;

public interface MissingRepository extends MongoRepository<MissingIngredients, Integer>{

    @SuppressWarnings("null")
    Optional<MissingIngredients> findById(int id);

}
