package com.jlgdev.ceres.models.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jlgdev.ceres.models.dataAccessObject.AlimentDAO;
import com.jlgdev.ceres.models.dataAccessObject.IngredientDAO;
import com.jlgdev.ceres.models.dataAccessObject.RecipeDAO;
import com.jlgdev.ceres.models.jsonToObject.IngredientJTO;
import com.jlgdev.ceres.models.jsonToObject.NutritionRecipeJTO;
import com.jlgdev.ceres.models.jsonToObject.RecipeJTO;
import com.jlgdev.ceres.services.AlimentService;

@Component
public class MapperRecipe {

    @Autowired
    public AlimentService alimentService;

    public RecipeDAO mapRecipe(RecipeJTO recipeJTO) {

        RecipeDAO recipeDAO = new RecipeDAO(); 

        recipeDAO.setId(Objects.requireNonNullElse(recipeJTO.getId(), ""));
        recipeDAO.setTitleEn(Objects.requireNonNullElse(recipeJTO.getTitle(), ""));
        recipeDAO.setIngredients(mapIngredients(recipeJTO.getNutrition()));
        recipeDAO.setTags(Objects.requireNonNullElse(recipeJTO.getTags(), new ArrayList<>()));
        recipeDAO.setVegetarian(Objects.requireNonNullElse(recipeJTO.isVegetarian(), false));
        recipeDAO.setVegan(Objects.requireNonNullElse(recipeJTO.isVegan(), false));
        recipeDAO.setGlutenFree(Objects.requireNonNullElse(recipeJTO.isGlutenFree(), false));
        recipeDAO.setDairyFree(Objects.requireNonNullElse(recipeJTO.isDairyFree(), false));
        recipeDAO.setPreparationMinutes(Objects.requireNonNullElse(recipeJTO.getPreparationMinutes(), -1));
        recipeDAO.setCookingMinutes(Objects.requireNonNullElse(recipeJTO.getCookingMinutes(), -1));
        recipeDAO.setTotalMinutes(Objects.requireNonNullElse(recipeJTO.getTotalMinutes(), -1));
        recipeDAO.setLikes(Objects.requireNonNullElse(recipeJTO.getLikes(), -1));
        recipeDAO.setHealthScore(Objects.requireNonNullElse(recipeJTO.getHealthScore(), -1));
        recipeDAO.setImage(Objects.requireNonNullElse(recipeJTO.getImage(), ""));
        recipeDAO.setImageType(Objects.requireNonNullElse(recipeJTO.getImageType(), ""));
        recipeDAO.setSourceURL(Objects.requireNonNullElse(recipeJTO.getSourceURL(), ""));
        recipeDAO.setSpoonacularURL(Objects.requireNonNullElse(recipeJTO.getSpoonacularURL(), ""));

        return recipeDAO;
    }

    public List<IngredientDAO> mapIngredients(NutritionRecipeJTO nutrition) {
        List<IngredientDAO> ingredients = new ArrayList<>();

        for (IngredientJTO ingredientJTO : nutrition.getIngredients()) {
            IngredientDAO ingredientDAO = new IngredientDAO();
            AlimentDAO linkedAliment = alimentService.getAlimentById(ingredientJTO.getIdAliment()).orElse(new AlimentDAO(ingredientJTO.getIdAliment()));

            ingredientDAO.setQuantity(Objects.requireNonNullElse(ingredientJTO.getQuantity(), -1.0));
            ingredientDAO.setUnit(Objects.requireNonNullElse(ingredientJTO.getUnit(), ""));
            ingredientDAO.setNameFromApi(Objects.requireNonNullElse(ingredientJTO.getNameFromApi(), ""));
            ingredientDAO.setAliment(linkedAliment);

            ingredients.add(ingredientDAO);
        }
    
        return ingredients;
    }

}
