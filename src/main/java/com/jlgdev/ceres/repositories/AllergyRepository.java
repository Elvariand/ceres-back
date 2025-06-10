package com.jlgdev.ceres.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jlgdev.ceres.models.dataAccessObject.AllergyDAO;

public interface AllergyRepository extends MongoRepository<AllergyDAO, String>{

    @SuppressWarnings("null")
    Optional<AllergyDAO> findById(String id);

}