package com.jlgdev.ceres.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import com.jlgdev.ceres.models.mongo.Aliment;

public interface AlimentRepository extends MongoRepository<Aliment, String>{

    @SuppressWarnings("null")
    Optional<Aliment> findById(@NonNull String id);
    List<Aliment> findByNameEn(String nameEn);
    List<Aliment> findByNameEnContaining(String nameEn);
    List<Aliment> findByAisle(String aisle);
    List<Aliment> findByAisleContaining(String aisle);
    List<Aliment> findByCategoryPath(String categoryPath);
    List<Aliment> findByCategoryPathContaining(String categoryPath);
}
