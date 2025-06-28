package com.jlgdev.ceres.models.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jlgdev.ceres.models.dataTransferObject.NoSQL.AlimentDTO;
import com.jlgdev.ceres.models.dataTransferObject.NoSQL.IngredientDTO;
import com.jlgdev.ceres.models.dataTransferObject.NoSQL.RecipeDTO;
import com.jlgdev.ceres.models.jsonToObject.IngredientJTO;
import com.jlgdev.ceres.models.jsonToObject.NutritionRecipeJTO;
import com.jlgdev.ceres.models.jsonToObject.RecipeJTO;
import com.jlgdev.ceres.services.AlimentService;

@Component
public class RecipeMapper {

    @Autowired
    public AlimentService alimentService;

    public RecipeDTO mapRecipe(RecipeJTO recipeJTO) {

        RecipeDTO recipeDTO = new RecipeDTO(); 

        recipeDTO.setId(Objects.requireNonNullElse(recipeJTO.getId(), ""));
        recipeDTO.setTitleEn(Objects.requireNonNullElse(recipeJTO.getTitle(), ""));
        recipeDTO.setIngredients(mapIngredients(recipeJTO.getNutrition()));
        recipeDTO.setTags(Objects.requireNonNullElse(recipeJTO.getTags(), new ArrayList<>()));
        recipeDTO.setVegetarian(Objects.requireNonNullElse(recipeJTO.getVegetarian(), RecipeDTO.OK));
        recipeDTO.setVegan(Objects.requireNonNullElse(recipeJTO.getVegan(), RecipeDTO.OK));
        recipeDTO.setGlutenfree(Objects.requireNonNullElse(recipeJTO.getGlutenFree(), RecipeDTO.OK));
        recipeDTO.setDairyfree(Objects.requireNonNullElse(recipeJTO.getDairyFree(), RecipeDTO.OK));
        recipeDTO.setPreparationMinutes(Objects.requireNonNullElse(recipeJTO.getPreparationMinutes(), -1));
        recipeDTO.setCookingMinutes(Objects.requireNonNullElse(recipeJTO.getCookingMinutes(), -1));
        recipeDTO.setTotalMinutes(Objects.requireNonNullElse(recipeJTO.getTotalMinutes(), -1));
        recipeDTO.setLikes(Objects.requireNonNullElse(recipeJTO.getLikes(), -1));
        recipeDTO.setHealthScore(Objects.requireNonNullElse(recipeJTO.getHealthScore(), -1));
        recipeDTO.setImage(Objects.requireNonNullElse(recipeJTO.getImage(), ""));
        recipeDTO.setImageType(Objects.requireNonNullElse(recipeJTO.getImageType(), ""));
        recipeDTO.setSourceURL(Objects.requireNonNullElse(recipeJTO.getSourceURL(), ""));
        recipeDTO.setSpoonacularURL(Objects.requireNonNullElse(recipeJTO.getSpoonacularURL(), ""));

        return recipeDTO;
    }

    public List<IngredientDTO> mapIngredients(NutritionRecipeJTO nutrition) {
        List<IngredientDTO> ingredients = new ArrayList<>();

        for (IngredientJTO ingredientJTO : nutrition.getIngredients()) {
            IngredientDTO ingredientDTO = new IngredientDTO();
            AlimentDTO linkedAliment = alimentService.getAlimentById(ingredientJTO.getIdAliment()).orElse(new AlimentDTO(ingredientJTO.getIdAliment()));

            ingredientDTO.setQuantity(Objects.requireNonNullElse(ingredientJTO.getQuantity(), -1.0));
            ingredientDTO.setUnit(Objects.requireNonNullElse(ingredientJTO.getUnit(), ""));
            ingredientDTO.setNameFromApi(Objects.requireNonNullElse(ingredientJTO.getNameFromApi(), ""));
            ingredientDTO.setAliment(linkedAliment);

            ingredients.add(ingredientDTO);
        }
    
        return ingredients;
    }

}
