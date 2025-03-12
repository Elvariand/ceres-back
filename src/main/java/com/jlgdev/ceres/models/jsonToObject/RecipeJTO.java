package com.jlgdev.ceres.models.jsonToObject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private boolean vegetarian;

    @JsonProperty("vegan")
    private boolean vegan;

    @JsonProperty("glutenFree")
    private boolean glutenFree;

    @JsonProperty("dairyFree")
    private boolean dairyFree;

    @JsonProperty("preparationMinutes")
    private int preparationMinutes;

    @JsonProperty("cookingMinutes")
    private int cookingMinutes;

    @JsonProperty("aggregateLikes")
    private int likes;

    @JsonProperty("healthScore")
    private int healthScore;
    
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

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
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

    public RecipeJTO() {
    }

    public RecipeJTO(String id, String title, NutritionRecipeJTO nutrition, List<String> tags, boolean vegetarian,
            boolean vegan, boolean glutenFree, boolean dairyFree, int preparationMinutes, int cookingMinutes, int likes,
            int healthScore, String image, String imageType, String sourceURL, String spoonacularURL) {
        this.id = id;
        this.title = title;
        this.nutrition = nutrition;
        this.tags = tags;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.preparationMinutes = preparationMinutes;
        this.cookingMinutes = cookingMinutes;
        this.likes = likes;
        this.healthScore = healthScore;
        this.image = image;
        this.imageType = imageType;
        this.sourceURL = sourceURL;
        this.spoonacularURL = spoonacularURL;
    }

    @Override
    public String toString() {
        return "RecipeJTO [id=" + id + ", title=" + title + ", nutrition=" + nutrition + ", tags=" + tags
                + ", vegetarian=" + vegetarian + ", vegan=" + vegan + ", glutenFree=" + glutenFree + ", dairyFree="
                + dairyFree + ", preparationMinutes=" + preparationMinutes + ", cookingMinutes=" + cookingMinutes
                + ", likes=" + likes + ", healthScore=" + healthScore + ", image=" + image + ", imageType=" + imageType
                + ", sourceURL=" + sourceURL + ", spoonacularURL=" + spoonacularURL + "]";
    }

    
}
