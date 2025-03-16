package com.jlgdev.ceres.models.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

public class SearchForm {

    @JsonProperty("ingredients")
    private String ingredients;
    
    @JsonProperty("ingredient-radio1")
    private boolean allIngredient;
    
    @JsonProperty("ingredient-radio2")
    private boolean atLeastOneIngredient;
    
    @JsonProperty("ingredient-radio")
    private String allOrOneIngredient;
    
    @JsonProperty("forbidden")
    private String withoutIngredient;
    
    @JsonProperty("preparation-time")
    @JsonSetter(nulls = Nulls.SKIP)
    private int preparationTime = -1;
    
    @JsonProperty("cooking-time")
    @JsonSetter(nulls = Nulls.SKIP)
    private int cookingTime = -1;
    
    @JsonProperty("total-time")
    @JsonSetter(nulls = Nulls.SKIP)
    private int totalTime = -1;
    
    @JsonProperty("restriction")
    private String restriction;
    
    @JsonProperty("restriction-signal")
    private boolean signalRestriction;
    
    @JsonProperty("restriction-filter")
    private boolean filterRestriction;
    
    @JsonProperty("restriction-radio")
    private String signalOrFilterRestriction;
    
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

    public boolean isAtLeastOneIngredient() {
        return atLeastOneIngredient;
    }

    public void setAtLeastOneIngredient(boolean atLeastOneIngredient) {
        this.atLeastOneIngredient = atLeastOneIngredient;
    }

    public String getAllOrOneIngredient() {
        return allOrOneIngredient;
    }

    public void setAllOrOneIngredient(String allOrOneIngredient) {
        this.allOrOneIngredient = allOrOneIngredient;
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

    public boolean isSignalRestriction() {
        return signalRestriction;
    }

    public void setSignalRestriction(boolean signalRestriction) {
        this.signalRestriction = signalRestriction;
    }

    public boolean isFilterRestriction() {
        return filterRestriction;
    }

    public void setFilterRestriction(boolean filterRestriction) {
        this.filterRestriction = filterRestriction;
    }

    public String getSignalOrFilterRestriction() {
        return signalOrFilterRestriction;
    }

    public void setSignalOrFilterRestriction(String signalOrFilterRestriction) {
        this.signalOrFilterRestriction = signalOrFilterRestriction;
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

    
}
