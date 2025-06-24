package com.jlgdev.ceres.models.jsonToObject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.jlgdev.ceres.models.dataTransferObject.RecipeDTO;

public class RecipeJTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("nutrition")
    private NutritionRecipeJTO nutrition;

    @JsonProperty("dishTypes")
    @JsonAlias({"cuisines","diets","occasions"})
    private List<String> tags;

    @JsonProperty("vegetarian")
    @JsonSetter(nulls = Nulls.SKIP)
    private byte vegetarian = RecipeDTO.OK;

    @JsonProperty("vegan")
    @JsonSetter(nulls = Nulls.SKIP)
    private byte vegan = RecipeDTO.OK;

    @JsonProperty("glutenFree")
    @JsonSetter(nulls = Nulls.SKIP)
    private byte glutenFree = RecipeDTO.OK;

    @JsonProperty("dairyFree")
    @JsonSetter(nulls = Nulls.SKIP)
    private byte dairyFree = RecipeDTO.OK;

    @JsonProperty("preparationMinutes")
    @JsonSetter(nulls = Nulls.SKIP)
    private int preparationMinutes = -1;
    
    @JsonProperty("cookingMinutes")
    @JsonSetter(nulls = Nulls.SKIP)
    private int cookingMinutes = -1;
    
    @JsonProperty("readyInMinutes")
    @JsonSetter(nulls = Nulls.SKIP)
    private int totalMinutes = -1;
    
    @JsonProperty("aggregateLikes")
    @JsonSetter(nulls = Nulls.SKIP)
    private int likes = -1;
    
    @JsonProperty("healthScore")
    @JsonSetter(nulls = Nulls.SKIP)
    private int healthScore = -1;
    
    @JsonProperty("image")
    private String image;
    
    @JsonProperty("imageType")
    private String imageType;
    
    @JsonProperty("sourceUrl")
    private String sourceURL;
    
    @JsonProperty("spoonacularSourceUrl")
    private String spoonacularURL;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getPreparationMinutes() {
        return preparationMinutes;
    }

    public void setPreparationMinutes(int preparationMinutes) {
        this.preparationMinutes = preparationMinutes;
    }

    public int getCookingMinutes() {
        return cookingMinutes;
    }

    public void setCookingMinutes(int cookingMinutes) {
        this.cookingMinutes = cookingMinutes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public String getSpoonacularURL() {
        return spoonacularURL;
    }

    public void setSpoonacularURL(String spoonacularURL) {
        this.spoonacularURL = spoonacularURL;
    }

    public NutritionRecipeJTO getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutritionRecipeJTO nutrition) {
        this.nutrition = nutrition;
    }

    
    public int getTotalMinutes() {
        return totalMinutes;
    }
    
    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }
    
    public byte getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(byte vegetarian) {
        this.vegetarian = vegetarian;
    }

    public byte getVegan() {
        return vegan;
    }

    public void setVegan(byte vegan) {
        this.vegan = vegan;
    }

    public byte getGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(byte glutenFree) {
        this.glutenFree = glutenFree;
    }

    public byte getDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(byte dairyFree) {
        this.dairyFree = dairyFree;
    }

    public RecipeJTO() {
    }

    @Override
    public String toString() {
        return "RecipeJTO [id=" + id + ", title=" + title + ", nutrition=" + nutrition + ", tags=" + tags
                + ", vegetarian=" + vegetarian + ", vegan=" + vegan + ", glutenFree=" + glutenFree + ", dairyFree="
                + dairyFree + ", preparationMinutes=" + preparationMinutes + ", cookingMinutes=" + cookingMinutes
                + ", totalMinutes=" + totalMinutes + ", likes=" + likes + ", healthScore=" + healthScore + ", image="
                + image + ", imageType=" + imageType + ", sourceURL=" + sourceURL + ", spoonacularURL=" + spoonacularURL
                + "]";
    }
}
