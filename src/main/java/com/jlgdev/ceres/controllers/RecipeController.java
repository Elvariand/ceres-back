package com.jlgdev.ceres.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jlgdev.ceres.models.dataAccessObject.RecipeDAO;
import com.jlgdev.ceres.services.AlimentService;
import com.jlgdev.ceres.services.RecipeService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = "*")
// @Transactional
public class RecipeController {

    @Autowired
    private AlimentService alimentService;

    @Autowired
    private RecipeService recipeService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{number}")
    public @ResponseBody List<RecipeDAO> getRecipes(@RequestParam int number) {
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

}
