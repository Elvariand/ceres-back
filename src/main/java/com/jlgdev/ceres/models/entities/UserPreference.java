package com.jlgdev.ceres.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "preferences")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    private String foodIn;

    private Boolean allIngredients;

    private String foodOut;

    private int preparationTime;

    private int cookingTime;

    private int totalTime;

    private String restriction;

    private Boolean filter;

    private String sortBy;

    private int serving;



    public Long  getId() {
        return id;
    }

    public void setId(Long  id) {
        this.id = id;
    }

    public String getFoodIn() {
        return foodIn;
    }

    public void setFoodIn(String foodIn) {
        this.foodIn = foodIn;
    }

    public Boolean getAllIngredients() {
        return allIngredients;
    }

    public void setAllIngredients(Boolean allIngredients) {
        this.allIngredients = allIngredients;
    }

    public String getFoodOut() {
        return foodOut;
    }

    public void setFoodOut(String foodOut) {
        this.foodOut = foodOut;
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

    public Boolean getFilter() {
        return filter;
    }

    public void setFilter(Boolean filter) {
        this.filter = filter;
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

    @Override
    public String toString() {
        return "UserPreference [id=" + id + ", foodIn=" + foodIn + ", allIngredients=" + allIngredients
                + ", foodOut=" + foodOut + ", preparationTime=" + preparationTime + ", cookingTime=" + cookingTime
                + ", totalTime=" + totalTime + ", restriction=" + restriction + ", filter=" + filter + ", sortBy="
                + sortBy + ", serving=" + serving + "]";
    }

    public UserPreference() {
        this.foodIn = null;
        this.allIngredients = null;
        this.foodOut = null;
        this.preparationTime = 0;
        this.cookingTime = 0;
        this.totalTime = 0;
        this.restriction = null;
        this.filter = null;
        this.sortBy = null;
        this.serving = 4;
    }

    
}
