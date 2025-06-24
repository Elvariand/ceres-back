package com.jlgdev.ceres.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;

import com.jlgdev.ceres.models.dataTransferObject.AlimentDTO;


public interface AlimentRepository extends MongoRepository<AlimentDTO, String>{

    @SuppressWarnings("null")
    Optional<AlimentDTO> findById(@NonNull String id);
    List<AlimentDTO> findByNameEn(String nameEn);
    List<AlimentDTO> findByNameEnContaining(String nameEn);
    List<AlimentDTO> findByNameFr(String nameFr);
    List<AlimentDTO> findByNameFrContaining(String nameFr);
    List<AlimentDTO> findByAisle(String aisle);
    List<AlimentDTO> findByAisleContaining(String aisle);
    List<AlimentDTO> findByCategoryPath(String categoryPath);
    List<AlimentDTO> findByCategoryPathContaining(String categoryPath);

    @Query("{vegan : 1}")
    List<AlimentDTO> findAllVegan();

    @Query("{vegan : 0}")
    List<AlimentDTO> findAllNonVegan();

    @Query("{vegetarian : 1}")
    List<AlimentDTO> findAllVegetarian();

    @Query("{vegetarian : 0}")
    List<AlimentDTO> findAllNonVegetarian();

    @Query("{categoryPath: {$exists: true, $type: \"array\", $eq: [] } }"
    + "$and {categoryPathEn: {$exists: true, $type: \"array\", $ne: [] } }")
    List<AlimentDTO> findAllMissingPath();
}
