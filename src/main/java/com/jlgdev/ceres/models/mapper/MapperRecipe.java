package com.jlgdev.ceres.models.mapper;

import java.util.ArrayList;
import java.util.List;

import com.jlgdev.ceres.models.dataAccessObject.AlimentDAO;
import com.jlgdev.ceres.models.dataAccessObject.IngredientDAO;
import com.jlgdev.ceres.models.dataAccessObject.RecipeDAO;
import com.jlgdev.ceres.models.jsonToObject.IngredientJTO;
import com.jlgdev.ceres.models.jsonToObject.NutritionRecipeJTO;
import com.jlgdev.ceres.models.jsonToObject.RecipeJTO;
import com.jlgdev.ceres.services.AlimentService;


public abstract class MapperRecipe {

    private static AlimentService alimentService;

    public static RecipeDAO mapRecipe(RecipeJTO recipeJTO) {

        RecipeDAO recipeDAO = new RecipeDAO(); 

        recipeDAO.setId(recipeJTO.getId());
        recipeDAO.setTitleEn(recipeJTO.getTitle());
        recipeDAO.setIngredients(mapIngredients(recipeJTO.getNutrition()));
        recipeDAO.setTags(recipeJTO.getTags());
        recipeDAO.setVegetarian(recipeJTO.isVegetarian());
        recipeDAO.setVegan(recipeJTO.isVegan());
        recipeDAO.setGlutenFree(recipeJTO.isGlutenFree());
        recipeDAO.setDairyFree(recipeJTO.isDairyFree());
        recipeDAO.setPreparationMinutes(recipeJTO.getPreparationMinutes());
        recipeDAO.setCookingMinutes(recipeJTO.getCookingMinutes());
        recipeDAO.setLikes(recipeJTO.getLikes());
        recipeDAO.setHealthScore(recipeJTO.getHealthScore());
        recipeDAO.setImage(recipeJTO.getImage());
        recipeDAO.setImageType(recipeJTO.getImageType());
        recipeDAO.setSourceURL(recipeJTO.getSourceURL());
        recipeDAO.setSpoonacularURL(recipeJTO.getSpoonacularURL());

        return recipeDAO;
    }

    public static List<IngredientDAO> mapIngredients(NutritionRecipeJTO nutrition) {
        List<IngredientDAO> ingredients = new ArrayList<>();

        for (IngredientJTO ingredientJTO : nutrition.getIngredients()) {
            IngredientDAO ingredientDAO = new IngredientDAO();

            ingredientDAO.setQuantity(ingredientJTO.getQuantity());
            ingredientDAO.setUnit(ingredientJTO.getUnit());
            ingredientDAO.setAliment(alimentService.getAlimentById(ingredientJTO.getIdAliment()).orElse(new AlimentDAO()));

            ingredients.add(ingredientDAO);
        }
    
        return ingredients;
    }


    
}
