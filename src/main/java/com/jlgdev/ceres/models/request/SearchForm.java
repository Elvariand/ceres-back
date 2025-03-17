package com.jlgdev.ceres.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

public class SearchForm {

    @JsonProperty("ingredients")
    private String ingredients;
    
    @JsonProperty("allIngredient")
    private boolean allIngredient;

    @JsonProperty("withoutIngredient")
    private String withoutIngredient;
    
    @JsonProperty("preparationTime")
    @JsonSetter(nulls = Nulls.SKIP)
    private int preparationTime = -1;
    
    @JsonProperty("cookingTime")
    @JsonSetter(nulls = Nulls.SKIP)
    private int cookingTime = -1;
    
    @JsonProperty("totalTime")
    @JsonSetter(nulls = Nulls.SKIP)
    private int totalTime = -1;
    
    @JsonProperty("restriction")
    private String restriction;
    
    @JsonProperty("sort")
    private String sortBy;
    
    @JsonProperty("serving")
    @JsonSetter(nulls = Nulls.SKIP)
    private int serving = 4;

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isAllIngredient() {
        return allIngredient;
    }

    public void setAllIngredient(boolean allIngredient) {
        this.allIngredient = allIngredient;
    }

    public String getWithoutIngredient() {
        return withoutIngredient;
    }

    public void setWithoutIngredient(String withoutIngredient) {
        this.withoutIngredient = withoutIngredient;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public SearchForm() {
    }

    public String getFormattedString(String rawStr) {
        if (rawStr == null || rawStr.trim().equals("")) {
            return null;
        }
        String[] stringsArray = rawStr.split(",");
        String formattedString = "";

        for (String string : stringsArray) {
            formattedString += "/" + string + ( string.equals(stringsArray[stringsArray.length - 1]) ? "/i" : "/i, " ) ;
        }
        return formattedString;
    }
}
