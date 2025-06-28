package com.jlgdev.ceres.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jlgdev.ceres.models.dataTransferObject.NoSQL.AllergyDTO;

public interface AllergyRepository extends MongoRepository<AllergyDTO, String>{

    @SuppressWarnings("null")
    Optional<AllergyDTO> findById(String id);

    Optional<AllergyDTO> findByName(String name);

}