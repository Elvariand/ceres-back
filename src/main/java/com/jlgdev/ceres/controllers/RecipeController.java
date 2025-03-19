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
    public List<RecipeDAO> getFilteredRecipes(@RequestBody SearchForm searchForm) {

        String ingredients = searchForm.getFormattedString(searchForm.getIngredients()) ;
        Boolean allIngredient = searchForm.isAllIngredient() ;
        String withoutIngredient = searchForm.getFormattedString(searchForm.getWithoutIngredient()) ;
        int preparationTime = searchForm.getPreparationTime() ;
        int cookingTime = searchForm.getCookingTime() ;
        int totalTime = searchForm.getTotalTime() ;
        String restriction = searchForm.getRestriction() ;
        String sortBy = searchForm.getSortBy() ;
        int serving = searchForm.getServing() ;

        System.out.println("##########################################################");
        System.out.println("ingredients = " + ingredients);
        System.out.println("allIngredient = " + allIngredient);
        System.out.println("withoutIngredient = " + withoutIngredient);
        System.out.println("preparationTime = " + preparationTime);
        System.out.println("cookingTime = " + cookingTime);
        System.out.println("totalTime = " + totalTime);
        System.out.println("restriction = " + restriction);
        System.out.println("sortBy = " + sortBy);
        System.out.println("serving = " + serving);
        System.out.println("##########################################################");
        
        String queryString = "{ \"$and\": [\n";
        // String queryString = "{ \"ingredients.aliment.nameEn\": { \"$in\" : [/chicken/, /beef/] } }";

        if (ingredients != null) {
            queryString += "\t{ \"$or\": [\n\t\t{ \"ingredients.aliment.nameEn\" : { " + (allIngredient ? "\"$all\"" : "\"$in\"") + ": [ " + ingredients + " ] } },\n\t\t{ \"ingredients.nameFromApi\" : { " + (allIngredient ? "\"$all\"" : "\"$in\"") + ": [ " + ingredients + " ] } }\n\t\t] },\n";
        }

        if (withoutIngredient != null) {
            queryString += "\t{ \"$or\": [\n\t\t{ \"ingredients.aliment.nameEn\" : { \"$nin\": [ " + withoutIngredient + " ] } },\n\t\t{ \"ingredients.nameFromApi\" : { \"$nin\": [ " + withoutIngredient + " ] } }\n\t\t] },\n";
        }

        if (preparationTime > -1) {
            queryString += "\t{\"preparationMinutes\" : { $lte : " + preparationTime + " } },\n";
        }

        if (cookingTime > -1) {
            queryString += "\t{\"cookingMinutes\" : { $lte : " + cookingTime + " } },\n";
        }

        if (totalTime > -1) {
            queryString += "\t{\"totalMinutes\" : { $lte : " + totalTime + " } },\n";
        }

        queryString = queryString.trim();
        int queryLength = queryString.length();

        if (queryLength > 1 && queryString.charAt(queryLength-1) == ',') {
            queryString = queryString.substring(0, queryLength-1);
        }

        queryString = queryString + "\n\t]\n}";

        System.out.println("=================================================");
        System.out.println(queryString);
        System.out.println("=================================================");

        BasicQuery query = new BasicQuery(queryString);


        List<RecipeDAO> filteredRecipes = mongoTemplate.find(query, RecipeDAO.class);
        
        System.out.println("nombre de recettes trouvees : " + filteredRecipes.size());
        System.out.println(filteredRecipes.size() > 0 ? filteredRecipes.get(0).getTitleEn() : "none");
        return filteredRecipes;
        // return ResponseEntity.ok(filteredRecipes);
    }
    
}
