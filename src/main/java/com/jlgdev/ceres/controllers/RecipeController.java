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
        String restrictionRadio = searchForm.getRestrictionRadio() ;
        String sortBy = searchForm.getSortBy() ;
        int serving = searchForm.getServing() ;

        System.out.println("##########################################################");
        System.out.println("title = " + title);
        System.out.println("ingredients = " + ingredients);
        System.out.println("allIngredient = " + allIngredient);
        System.out.println("withoutIngredient = " + withoutIngredient);
        System.out.println("preparationTime = " + preparationTime);
        System.out.println("cookingTime = " + cookingTime);
        System.out.println("totalTime = " + totalTime);
        System.out.println("restriction = " + restriction);
        System.out.println("restrictionRadio = " + restrictionRadio);
        System.out.println("sortBy = " + sortBy);
        System.out.println("serving = " + serving);
        System.out.println("##########################################################");

        return ResponseEntity.ok(recipeService.searchByForm(searchForm));
    }
    
}
