package com.jlgdev.ceres.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jlgdev.ceres.models.dataTransferObject.MissingIngredientsDTO;

public interface MissingRepository extends MongoRepository<MissingIngredientsDTO, Integer>{

    Optional<MissingIngredientsDTO> findById(int id);

}
