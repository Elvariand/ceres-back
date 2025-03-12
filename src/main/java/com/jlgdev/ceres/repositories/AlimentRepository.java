package com.jlgdev.ceres.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import com.jlgdev.ceres.models.dataAccessObject.AlimentDAO;

public interface AlimentRepository extends MongoRepository<AlimentDAO, String>{

    @SuppressWarnings("null")
    Optional<AlimentDAO> findById(@NonNull String id);
    List<AlimentDAO> findByNameEn(String nameEn);
    List<AlimentDAO> findByNameEnContaining(String nameEn);
    List<AlimentDAO> findByAisle(String aisle);
    List<AlimentDAO> findByAisleContaining(String aisle);
    List<AlimentDAO> findByCategoryPath(String categoryPath);
    List<AlimentDAO> findByCategoryPathContaining(String categoryPath);
}
