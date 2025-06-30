package com.jlgdev.ceres.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.dataTransferObject.NoSQL.RecipeDTO;
import com.jlgdev.ceres.models.request.SearchForm;
import com.jlgdev.ceres.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public Iterable<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<RecipeDTO> getRecipeById(String id) {
        return recipeRepository.findById(id);
    }

    public Iterable<RecipeDTO> getRecipeByTitle(String title) {
        return recipeRepository.findByTitleEn(title);
    }

    public Iterable<RecipeDTO> getRecipeByTitleContaining(String title) {
        return recipeRepository.findByTitleEnContaining(title);
    }

    public RecipeDTO save(RecipeDTO Recipe) {
        return recipeRepository.save(Recipe);
    }


    public List<RecipeDTO> searchByForm(SearchForm searchForm) {
        List<Criteria> andCriteria = new ArrayList<>();

        String title = searchForm.getTitle();
        if (title != null && !title.isEmpty()) {
            andCriteria.add(Criteria.where("titleFr").regex(title, "i"));
        }

        String ingredients = searchForm.getFormattedString(searchForm.getIngredients());
        if (ingredients != null && !ingredients.isEmpty()) {
            String[] ingList = ingredients.split(",");
            Criteria ingredientCriteria = Criteria.where("ingredients.aliment.nameFr");
            if (searchForm.getAllIngredient().toLowerCase().equals("tous")) {
                ingredientCriteria = ingredientCriteria.all((Object[]) ingList);
            } else {
                ingredientCriteria = ingredientCriteria.in((Object[]) ingList);
            }
            andCriteria.add(ingredientCriteria);
        }

        String withoutIngredient = searchForm.getFormattedString(searchForm.getWithoutIngredient());
        if (withoutIngredient != null && !withoutIngredient.isEmpty()) {
            String[] withoutList = withoutIngredient.split(",");
            andCriteria.add(Criteria.where("ingredients.aliment.nameFr").nin((Object[]) withoutList));
        }

        if (searchForm.getPreparationTime() > -1) {
            andCriteria.add(Criteria.where("preparationMinutes").lte(searchForm.getPreparationTime()));
        }

        if (searchForm.getCookingTime() > -1) {
            andCriteria.add(Criteria.where("cookingMinutes").lte(searchForm.getCookingTime()));
        }

        if (searchForm.getTotalTime() > -1) {
            andCriteria.add(Criteria.where("totalMinutes").lte(searchForm.getTotalTime()));
        }

        if (searchForm.getRestriction() != null && !searchForm.getRestriction().isEmpty()) {
            andCriteria.add(Criteria.where("restrictions").in(searchForm.getRestriction()));
        }

        Query query = new Query();
        if (!andCriteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(andCriteria.toArray(new Criteria[0])));
        }

        // Gestion du tri
        if (searchForm.getSortBy() != null && !searchForm.getSortBy().isEmpty()) {
            query.with(Sort.by(Sort.Direction.ASC, searchForm.getSortBy()));
        }

        // Collation pour ignorer accents et casse
        query.collation(Collation.of("fr").strength(Collation.ComparisonLevel.primary()));

        return mongoTemplate.find(query, RecipeDTO.class);
    }
}