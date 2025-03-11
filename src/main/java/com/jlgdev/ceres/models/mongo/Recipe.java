package com.jlgdev.ceres.models.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document("recipes")
public class Recipe {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("ingredients")
    private List<Ingredient> ingredients;

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
    
    @JsonProperty("sourceURL")
    private String sourceURL;
    
    @JsonProperty("spoonacularURL")
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
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

    public Recipe() {
    }

    public Recipe(String title, List<Ingredient> ingredients, List<String> tags, boolean vegetarian, boolean vegan,
            boolean glutenFree, boolean dairyFree, int preparationMinutes, int cookingMinutes, int likes,
            int healthScore, String image, String imageType, String sourceURL, String spoonacularURL) {
        this.title = title;
        this.ingredients = ingredients;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
        result = prime * result + (vegetarian ? 1231 : 1237);
        result = prime * result + (vegan ? 1231 : 1237);
        result = prime * result + (glutenFree ? 1231 : 1237);
        result = prime * result + (dairyFree ? 1231 : 1237);
        result = prime * result + preparationMinutes;
        result = prime * result + cookingMinutes;
        result = prime * result + likes;
        result = prime * result + healthScore;
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((imageType == null) ? 0 : imageType.hashCode());
        result = prime * result + ((sourceURL == null) ? 0 : sourceURL.hashCode());
        result = prime * result + ((spoonacularURL == null) ? 0 : spoonacularURL.hashCode());
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
        Recipe other = (Recipe) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (ingredients == null) {
            if (other.ingredients != null)
                return false;
        } else if (!ingredients.equals(other.ingredients))
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        if (vegetarian != other.vegetarian)
            return false;
        if (vegan != other.vegan)
            return false;
        if (glutenFree != other.glutenFree)
            return false;
        if (dairyFree != other.dairyFree)
            return false;
        if (preparationMinutes != other.preparationMinutes)
            return false;
        if (cookingMinutes != other.cookingMinutes)
            return false;
        if (likes != other.likes)
            return false;
        if (healthScore != other.healthScore)
            return false;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (imageType == null) {
            if (other.imageType != null)
                return false;
        } else if (!imageType.equals(other.imageType))
            return false;
        if (sourceURL == null) {
            if (other.sourceURL != null)
                return false;
        } else if (!sourceURL.equals(other.sourceURL))
            return false;
        if (spoonacularURL == null) {
            if (other.spoonacularURL != null)
                return false;
        } else if (!spoonacularURL.equals(other.spoonacularURL))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", title=" + title + ", ingredients=" + ingredients + ", tags=" + tags
                + ", vegetarian=" + vegetarian + ", vegan=" + vegan + ", glutenFree=" + glutenFree + ", dairyFree="
                + dairyFree + ", preparationMinutes=" + preparationMinutes + ", cookingMinutes=" + cookingMinutes
                + ", likes=" + likes + ", healthScore=" + healthScore + ", image=" + image + ", imageType=" + imageType
                + ", sourceURL=" + sourceURL + ", spoonacularURL=" + spoonacularURL + "]";
    }

    
}
