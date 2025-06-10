package com.jlgdev.ceres.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jlgdev.ceres.models.dataAccessObject.MissingIngredients;

public interface MissingRepository extends MongoRepository<MissingIngredients, Integer>{

    Optional<MissingIngredients> findById(int id);

}
