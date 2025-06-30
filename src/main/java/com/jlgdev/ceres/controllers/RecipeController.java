package com.jlgdev.ceres.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import com.jlgdev.ceres.models.dataTransferObject.NoSQL.RecipeDTO;
import com.jlgdev.ceres.models.request.SearchForm;
// import com.jlgdev.ceres.services.AlimentService;
import com.jlgdev.ceres.services.RecipeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = "*")
// @Transactional
public class RecipeController {

    // @Autowired
    // private AlimentService alimentService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private MongoOperations mongoTemplate;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{number}")
    public @ResponseBody List<RecipeDTO> getRecipes(@PathVariable int number) {
        List<RecipeDTO> recipes = StreamSupport.stream(recipeService.getAllRecipes().spliterator(), false).collect(Collectors.toList());
        Collections.shuffle(recipes);
        int counter = 0;
        List<RecipeDTO> recipesToFrontend = new ArrayList<>();

        for (RecipeDTO recipeDTO : recipes) {
            recipesToFrontend.add(recipeDTO);
            if (++counter >= number) {
                break;
            }
        }

        return recipesToFrontend;
    }

    @PostMapping("/search")
    public ResponseEntity<List<RecipeDTO>> getFilteredRecipes(@RequestBody SearchForm searchForm) {

        String title = searchForm.getTitle() ;
        String ingredients = searchForm.getFormattedString(searchForm.getIngredients()) ;
        String allIngredient = searchForm.getAllIngredient() ;
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

        if (title != null) {
            queryString += "\t{\"titleFr\" : /" + title + "/i },\n";
        }

        if (ingredients != null) {
            queryString += "\t{\"ingredients.aliment.nameFr\" : { " + (allIngredient.toLowerCase().equals("tous") ? "\"$all\"" : "\"$in\"") + ": [ " + ingredients + " ] } },\n";
        }

        if (withoutIngredient != null) {
            queryString += "\t{\"ingredients.aliment.nameFr\" : { \"$nin\": [ " + withoutIngredient + " ] } },\n";
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


        List<RecipeDTO> filteredRecipes = mongoTemplate.find(query, RecipeDTO.class);
        
        System.out.println("nombre de recettes trouvees : " + filteredRecipes.size());
        System.out.println(filteredRecipes.size() > 0 ? filteredRecipes.get(0).getTitleFr() : "none");
        // return filteredRecipes;
        return ResponseEntity.ok(filteredRecipes);
    }
    
}
