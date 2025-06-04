package com.jlgdev.ceres.models.dataAccessObject;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("recipes")
public class RecipeDAO {

    private String id;

    private String title;

    private String titleEn;

    private String titleFr;

    private List<IngredientDAO> ingredients;

    private List<String> tags;

    private boolean vegetarian;

    private boolean vegan;

    private boolean glutenFree;

    private boolean eggfree;

    private boolean nutfree;

    private boolean shellfishfree;

    private boolean seefoodfree;

    private boolean mustardfree;

    private boolean fishfree;

    private boolean celeryfree;

    private boolean soyfree;

    private boolean sulfiltfree;

    private boolean sesamefree;

    private boolean lupinefree;

    private boolean judaism;

    private boolean islam;

    private boolean seasonal;
    
    private boolean dairyFree;

    private int preparationMinutes;

    private int cookingMinutes;

    private int totalMinutes;
    
    private int likes;
    
    private int healthScore;
    
    private String image;
    
    private String imageType;
    
    private String sourceURL;
    
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

    public String getTitleEn() {
        return titleEn;
    }
    
    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }
    
    public String getTitleFr() {
        return titleFr;
    }
    
    public void setTitleFr(String titleFr) {
        this.titleFr = titleFr;
    }
    
    public List<IngredientDAO> getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(List<IngredientDAO> ingredients) {
        this.ingredients = ingredients;
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

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
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

    public RecipeDAO() {
    }

    public RecipeDAO(String id, String title, String titleEn, String titleFr, List<IngredientDAO> ingredients,
            List<String> tags, boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree,
            int preparationMinutes, int cookingMinutes, int totalMinutes, int likes, int healthScore, String image,
            String imageType, String sourceURL, String spoonacularURL) {
        this.id = id;
        this.title = title;
        this.titleEn = titleEn;
        this.titleFr = titleFr;
        this.ingredients = ingredients;
        this.tags = tags;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.preparationMinutes = preparationMinutes;
        this.cookingMinutes = cookingMinutes;
        this.totalMinutes = totalMinutes;
        this.likes = likes;
        this.healthScore = healthScore;
        this.image = image;
        this.imageType = imageType;
        this.sourceURL = sourceURL;
        this.spoonacularURL = spoonacularURL;
    }

    @Override
    public String toString() {
        return "RecipeDAO [id=" + id + ", title=" + title + ", titleEn=" + titleEn + ", titleFr=" + titleFr
                + ", ingredients=" + ingredients + ", tags=" + tags + ", vegetarian=" + vegetarian + ", vegan=" + vegan
                + ", glutenFree=" + glutenFree + ", dairyFree=" + dairyFree + ", preparationMinutes="
                + preparationMinutes + ", cookingMinutes=" + cookingMinutes + ", totalMinutes=" + totalMinutes
                + ", likes=" + likes + ", healthScore=" + healthScore + ", image=" + image + ", imageType=" + imageType
                + ", sourceURL=" + sourceURL + ", spoonacularURL=" + spoonacularURL + "]";
    }

}
