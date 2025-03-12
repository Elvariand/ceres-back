package com.jlgdev.ceres.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import com.jlgdev.ceres.models.jsonToObject.AlimentJTO;

public interface AlimentRepository extends MongoRepository<AlimentJTO, String>{

    @SuppressWarnings("null")
    Optional<AlimentJTO> findById(@NonNull String id);
    List<AlimentJTO> findByNameEn(String nameEn);
    List<AlimentJTO> findByNameEnContaining(String nameEn);
    List<AlimentJTO> findByAisle(String aisle);
    List<AlimentJTO> findByAisleContaining(String aisle);
    List<AlimentJTO> findByCategoryPath(String categoryPath);
    List<AlimentJTO> findByCategoryPathContaining(String categoryPath);
}
