package com.jlgdev.ceres.models.jsonToObject;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlimentJTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("aisle")
    private String aisle;

    @JsonProperty("image")
    private String image;

    @JsonProperty("nutrition")
    private NutritionJTO nutrition;

    @JsonProperty("categoryPath")
    private Set<String> categoryPath;

    @JsonProperty("possibleUnits")
    private Set<String> possibleUnit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public NutritionJTO getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutritionJTO nutrition) {
        this.nutrition = nutrition;
    }

    public Set<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(Set<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public Set<String> getPossibleUnit() {
        return possibleUnit;
    }

    public void setPossibleUnit(Set<String> possibleUnit) {
        this.possibleUnit = possibleUnit;
    }

    public AlimentJTO() {
    }

    @Override
    public String toString() {
        return "AlimentJTO [id=" + id + ", name=" + name + ", aisle=" + aisle + ", image=" + image + ", nutrition="
                + nutrition + ", categoryPath=" + categoryPath + ", possibleUnit=" + possibleUnit + "]";
    }

}
