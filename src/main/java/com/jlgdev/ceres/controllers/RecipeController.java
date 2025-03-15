package com.jlgdev.ceres.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jlgdev.ceres.models.dataAccessObject.RecipeDAO;
import com.jlgdev.ceres.models.request.SearchForm;
import com.jlgdev.ceres.services.AlimentService;
import com.jlgdev.ceres.services.RecipeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = "*")
// @Transactional
public class RecipeController {

    @Autowired
    private AlimentService alimentService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private MongoOperations mongoTemplate;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{number}")
    public @ResponseBody List<RecipeDAO> getRecipes(@PathVariable int number) {
        Iterable<RecipeDAO> recipes = recipeService.getAllRecipes();
        int counter = 0;
        List<RecipeDAO> recipesToFrontend = new ArrayList<>();

        for (RecipeDAO recipeDAO : recipes) {
            recipesToFrontend.add(recipeDAO);
            if (++counter >= number) {
                break;
            }
        }

        return recipesToFrontend;
    }

    @PostMapping("/search")
    public ResponseEntity<List<RecipeDAO>> getFilteredRecipes(@RequestBody SearchForm searchForm) {

        String ingredients = searchForm.getIngredients() ;
        Boolean allIngredient = searchForm.isAllIngredient() ;
        Boolean atLeastOneIngredient = searchForm.isAtLeastOneIngredient() ;
        String allOrOneIngredient = searchForm.getAllOrOneIngredient() ;
        String withoutIngredient = searchForm.getWithoutIngredient() ;
        int preparationTime = searchForm.getPreparationTime() ;
        int cookingTime = searchForm.getCookingTime() ;
        int totalTime = searchForm.getTotalTime() ;
        String restriction = searchForm.getRestriction() ;
        Boolean signalRestriction = searchForm.isSignalRestriction() ;
        Boolean filterRestriction = searchForm.isFilterRestriction() ;
        String signalOrFilterRestriction = searchForm.getSignalOrFilterRestriction() ;
        String sortBy = searchForm.getSortBy() ;
        String serving = searchForm.getServing() ;

        String queryString = "{";

        System.out.println("############################### allOrOne ###########################\n" + allOrOneIngredient);

        if (ingredients != null) {
            queryString += "{ ingredients.aliment.nameEn : { " + (allIngredient ? "$all" : "$in") + ": [ " + ingredients + " ] } },";
        }

        if (withoutIngredient != null) {
            queryString += "{ ingredients.aliment.nameEn : { $nin: [ " + withoutIngredient + " ] } },";
        }

        if (preparationTime > -1) {
            queryString += "{ preparationMinutes : { $lte : " + preparationTime + " } },";
        }

        if (cookingTime > -1) {
            queryString += "{ cookingMinutes : { $lte : " + cookingTime + " } },";
        }

        if (totalTime > -1) {
            queryString += "{ totalMinutes : { $lte : " + totalTime + " } },";
        }

        int queryLength = queryString.length();
        if (queryLength > 1 && queryString.charAt(queryLength-1) == ',') {
            queryString.substring(0, queryLength-1);
        }
        queryString += "}";

        BasicQuery query = new BasicQuery(queryString);


        List<RecipeDAO> filteredRecipes = mongoTemplate.find(query, RecipeDAO.class);
        
        return ResponseEntity.ok(filteredRecipes);
    }
    
}
