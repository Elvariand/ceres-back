package com.jlgdev.ceres.models.dataTransferObject.NoSQL;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("recipes")
public class RecipeDTO {
    public static final byte OK = 1;
    public static final byte WARNING = 2;
    public static final byte FORBIDDEN = 0;

    private String id;

    private String title;

    private String titleEn;

    private String titleFr;

    private List<IngredientDTO> ingredients;

    private List<String> tags;

    private byte vegetarian;

    private byte vegan;

    private byte arachidfree;

    private byte glutenfree;

    private byte eggfree;

    private byte nutfree;

    private byte shellfishfree;

    private byte seefoodfree;

    private byte mustardfree;

    private byte fishfree;

    private byte celeryfree;

    private byte soyfree;

    private byte sulfitfree;

    private byte sesamefree;

    private byte lupinefree;

    private byte judaism;

    private byte islam;

    private byte seasonal;
    
    private byte dairyfree;
    
    private boolean flagged;

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
    
    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
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

    public byte getArachidfree() {
        return arachidfree;
    }

    public void setArachidfree(byte arachidfree) {
        this.arachidfree = arachidfree;
    }

    public byte getGlutenfree() {
        return glutenfree;
    }

    public void setGlutenfree(byte glutenfree) {
        this.glutenfree = glutenfree;
    }

    public byte getEggfree() {
        return eggfree;
    }

    public void setEggfree(byte eggfree) {
        this.eggfree = eggfree;
    }

    public byte getNutfree() {
        return nutfree;
    }

    public void setNutfree(byte nutfree) {
        this.nutfree = nutfree;
    }

    public byte getShellfishfree() {
        return shellfishfree;
    }

    public void setShellfishfree(byte shellfishfree) {
        this.shellfishfree = shellfishfree;
    }

    public byte getSeefoodfree() {
        return seefoodfree;
    }

    public void setSeefoodfree(byte seefoodfree) {
        this.seefoodfree = seefoodfree;
    }

    public byte getMustardfree() {
        return mustardfree;
    }

    public void setMustardfree(byte mustardfree) {
        this.mustardfree = mustardfree;
    }

    public byte getFishfree() {
        return fishfree;
    }

    public void setFishfree(byte fishfree) {
        this.fishfree = fishfree;
    }

    public byte getCeleryfree() {
        return celeryfree;
    }

    public void setCeleryfree(byte celeryfree) {
        this.celeryfree = celeryfree;
    }

    public byte getSoyfree() {
        return soyfree;
    }

    public void setSoyfree(byte soyfree) {
        this.soyfree = soyfree;
    }

    public byte getSulfitfree() {
        return sulfitfree;
    }

    public void setSulfitfree(byte sulfitfree) {
        this.sulfitfree = sulfitfree;
    }

    public byte getSesamefree() {
        return sesamefree;
    }

    public void setSesamefree(byte sesamefree) {
        this.sesamefree = sesamefree;
    }

    public byte getLupinefree() {
        return lupinefree;
    }

    public void setLupinefree(byte lupinefree) {
        this.lupinefree = lupinefree;
    }

    public byte getJudaism() {
        return judaism;
    }

    public void setJudaism(byte judaism) {
        this.judaism = judaism;
    }

    public byte getIslam() {
        return islam;
    }

    public void setIslam(byte islam) {
        this.islam = islam;
    }

    public byte getSeasonal() {
        return seasonal;
    }

    public void setSeasonal(byte seasonal) {
        this.seasonal = seasonal;
    }

    public byte getDairyfree() {
        return dairyfree;
    }

    public void setDairyfree(byte dairyfree) {
        this.dairyfree = dairyfree;
    }
    
    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public RecipeDTO() {
    }

    @Override
    public String toString() {
        return "RecipeDTO [id=" + id + ", title=" + title + ", titleEn=" + titleEn + ", titleFr=" + titleFr
                + ", ingredients=" + ingredients + ", tags=" + tags + ", vegetarian=" + vegetarian + ", vegan=" + vegan
                + ", arachidfree=" + arachidfree + ", glutenfree=" + glutenfree + ", eggfree=" + eggfree + ", nutfree="
                + nutfree + ", shellfishfree=" + shellfishfree + ", seefoodfree=" + seefoodfree + ", mustardfree="
                + mustardfree + ", fishfree=" + fishfree + ", celeryfree=" + celeryfree + ", soyfree=" + soyfree
                + ", sulfitfree=" + sulfitfree + ", sesamefree=" + sesamefree + ", lupinefree=" + lupinefree
                + ", judaism=" + judaism + ", islam=" + islam + ", seasonal=" + seasonal + ", dairyfree=" + dairyfree
                + ", flagged=" + flagged + ", preparationMinutes=" + preparationMinutes + ", cookingMinutes="
                + cookingMinutes + ", totalMinutes=" + totalMinutes + ", likes=" + likes + ", healthScore="
                + healthScore + ", image=" + image + ", imageType=" + imageType + ", sourceURL=" + sourceURL
                + ", spoonacularURL=" + spoonacularURL + "]";
    }

}
