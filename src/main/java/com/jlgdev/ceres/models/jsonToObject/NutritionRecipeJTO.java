package com.jlgdev.ceres.models.jsonToObject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NutritionRecipeJTO {

    @JsonProperty("ingredients")
    private List<IngredientJTO> ingredients;



    public List<IngredientJTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientJTO> ingredients) {
        this.ingredients = ingredients;
    }

    public NutritionRecipeJTO() {
    }

    public NutritionRecipeJTO(List<IngredientJTO> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NutritionRecipeJTO other = (NutritionRecipeJTO) obj;
        if (ingredients == null) {
            if (other.ingredients != null)
                return false;
        } else if (!ingredients.equals(other.ingredients))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "NutritionRecipeJTO [ingredients=" + ingredients + "]";
    }

}
