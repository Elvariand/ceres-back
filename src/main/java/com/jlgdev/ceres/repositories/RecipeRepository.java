package com.jlgdev.ceres.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import com.jlgdev.ceres.models.jsonToObject.RecipeJTO;

public interface RecipeRepository extends MongoRepository<RecipeJTO, String>{

    @SuppressWarnings("null")
    Optional<RecipeJTO> findById(@NonNull String id);
    List<RecipeJTO> findByTitle(String title);
    List<RecipeJTO> findByTitleContaining(String title);
}
