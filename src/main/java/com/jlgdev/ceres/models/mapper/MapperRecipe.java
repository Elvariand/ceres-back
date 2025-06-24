package com.jlgdev.ceres.models.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jlgdev.ceres.models.dataTransferObject.AlimentDTO;
import com.jlgdev.ceres.models.dataTransferObject.IngredientDTO;
import com.jlgdev.ceres.models.dataTransferObject.RecipeDTO;
import com.jlgdev.ceres.models.jsonToObject.IngredientJTO;
import com.jlgdev.ceres.models.jsonToObject.NutritionRecipeJTO;
import com.jlgdev.ceres.models.jsonToObject.RecipeJTO;
import com.jlgdev.ceres.services.AlimentService;

@Component
public class MapperRecipe {

    @Autowired
    public AlimentService alimentService;

    public RecipeDTO mapRecipe(RecipeJTO recipeJTO) {

        RecipeDTO recipeDAO = new RecipeDTO(); 

        recipeDAO.setId(Objects.requireNonNullElse(recipeJTO.getId(), ""));
        recipeDAO.setTitleEn(Objects.requireNonNullElse(recipeJTO.getTitle(), ""));
        recipeDAO.setIngredients(mapIngredients(recipeJTO.getNutrition()));
        recipeDAO.setTags(Objects.requireNonNullElse(recipeJTO.getTags(), new ArrayList<>()));
        recipeDAO.setVegetarian(Objects.requireNonNullElse(recipeJTO.getVegetarian(), RecipeDTO.OK));
        recipeDAO.setVegan(Objects.requireNonNullElse(recipeJTO.getVegan(), RecipeDTO.OK));
        recipeDAO.setGlutenfree(Objects.requireNonNullElse(recipeJTO.getGlutenFree(), RecipeDTO.OK));
        recipeDAO.setDairyfree(Objects.requireNonNullElse(recipeJTO.getDairyFree(), RecipeDTO.OK));
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

    public List<IngredientDTO> mapIngredients(NutritionRecipeJTO nutrition) {
        List<IngredientDTO> ingredients = new ArrayList<>();

        for (IngredientJTO ingredientJTO : nutrition.getIngredients()) {
            IngredientDTO ingredientDAO = new IngredientDTO();
            AlimentDTO linkedAliment = alimentService.getAlimentById(ingredientJTO.getIdAliment()).orElse(new AlimentDTO(ingredientJTO.getIdAliment()));

            ingredientDAO.setQuantity(Objects.requireNonNullElse(ingredientJTO.getQuantity(), -1.0));
            ingredientDAO.setUnit(Objects.requireNonNullElse(ingredientJTO.getUnit(), ""));
            ingredientDAO.setNameFromApi(Objects.requireNonNullElse(ingredientJTO.getNameFromApi(), ""));
            ingredientDAO.setAliment(linkedAliment);

            ingredients.add(ingredientDAO);
        }
    
        return ingredients;
    }

}
