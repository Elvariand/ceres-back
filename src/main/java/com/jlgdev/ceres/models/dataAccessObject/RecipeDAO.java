package com.jlgdev.ceres.models.dataAccessObject;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("recipes")
public class RecipeDAO {

    private String id;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String titleEn;

    private String titleFr;

    private List<IngredientDAO> ingredients;

    private List<String> tags;

    private boolean vegetarian;

    private boolean vegan;

    private boolean glutenFree;

    private boolean dairyFree;

    private int preparationMinutes;

    private int cookingMinutes;

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

    public RecipeDAO(String id, String titleEn, String titleFr, List<IngredientDAO> ingredients, List<String> tags,
            boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, int preparationMinutes,
            int cookingMinutes, int likes, int healthScore, String image, String imageType, String sourceURL,
            String spoonacularURL) {
        this.id = id;
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
        result = prime * result + ((titleEn == null) ? 0 : titleEn.hashCode());
        result = prime * result + ((titleFr == null) ? 0 : titleFr.hashCode());
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
        RecipeDAO other = (RecipeDAO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (titleEn == null) {
            if (other.titleEn != null)
                return false;
        } else if (!titleEn.equals(other.titleEn))
            return false;
        if (titleFr == null) {
            if (other.titleFr != null)
                return false;
        } else if (!titleFr.equals(other.titleFr))
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
        return "RecipeDAO [id=" + id + ", titleEn=" + titleEn + ", titleFr=" + titleFr + ", ingredients=" + ingredients
                + ", tags=" + tags + ", vegetarian=" + vegetarian + ", vegan=" + vegan + ", glutenFree=" + glutenFree
                + ", dairyFree=" + dairyFree + ", preparationMinutes=" + preparationMinutes + ", cookingMinutes="
                + cookingMinutes + ", likes=" + likes + ", healthScore=" + healthScore + ", image=" + image
                + ", imageType=" + imageType + ", sourceURL=" + sourceURL + ", spoonacularURL=" + spoonacularURL + "]";
    }

    
}
