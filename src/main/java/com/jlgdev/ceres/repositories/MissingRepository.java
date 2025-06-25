package com.jlgdev.ceres.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jlgdev.ceres.models.dataTransferObject.NoSQL.MissingIngredientsDTO;

public interface MissingRepository extends MongoRepository<MissingIngredientsDTO, Integer>{

    Optional<MissingIngredientsDTO> findById(int id);

}
