package com.jlgdev.ceres.models.request;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.jlgdev.ceres.models.Utils.TextUtils;

public class SearchForm {

    @JsonProperty("title")
    private String title;

    @JsonProperty("ingredients")
    private String ingredients;

    @JsonProperty("allIngredient")
    private String allIngredient;

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

    @JsonProperty("restrictionRadio")
    private String restrictionRadio;

    @JsonProperty("sort")
    private String sortBy;

    @JsonProperty("serving")
    @JsonSetter(nulls = Nulls.SKIP)
    private int serving = 4;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllIngredient() {
        return allIngredient;
    }

    public void setAllIngredient(String allIngredient) {
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

    public String getRestrictionRadio() {
        return restrictionRadio;
    }

    public void setRestrictionRadio(String restrictionRadio) {
        this.restrictionRadio = restrictionRadio;
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
            formattedString += "/" + string + (string.equals(stringsArray[stringsArray.length - 1]) ? "/i" : "/i, ");
        }
        return formattedString;
    }

    public Set<String> translateRestrictions() {
        String rawRestrictions = this.getRestriction();
        if (rawRestrictions == null || rawRestrictions.trim().equals("")) {
            return new HashSet<>();
        }
        String[] arrayRestrictions = rawRestrictions.split(",");
        Set<String> formattedRestrictions = new HashSet<>();

        for (String str : arrayRestrictions) {
            String normalizedStr = TextUtils.removeAccents(str).toLowerCase();
            switch (normalizedStr) {
                case "vegetarien":
                case "vegetariens":
                case "vegetarienne":
                case "vegetariennes":
                    formattedRestrictions.add("vegetarian");
                    break;
                case "vegan":
                case "vegans":
                case "veganism":
                case "veganisme":
                    formattedRestrictions.add("vegan");
                    break;
                case "arachide":
                case "arachides":
                    formattedRestrictions.add("arachidfree");
                    break;
                case "gluten":
                    formattedRestrictions.add("glutenfree");
                    break;
                case "oeuf":
                case "oeufs":
                    formattedRestrictions.add("eggfree");
                    break;
                case "noix":
                case "fruits à coque":
                case "coque":
                    formattedRestrictions.add("nutfree");
                    break;
                case "coquillage":
                case "mollusque":
                case "coquillages":
                case "mollusques":
                    formattedRestrictions.add("shellfishfree");
                    break;
                case "fruits de mer":
                case "fruit de mer":
                    formattedRestrictions.add("seefoodfree");
                    break;
                case "mouarde":
                    formattedRestrictions.add("mustardfree");
                    break;
                case "poisson":
                case "poissons":
                    formattedRestrictions.add("fishfree");
                    break;
                case "celeri":
                    formattedRestrictions.add("celeryfree");
                    break;
                case "soja":
                    formattedRestrictions.add("soyfree");
                    break;
                case "sulfite":
                case "sulfites":
                    formattedRestrictions.add("sulfitefree");
                    break;
                case "sesame":
                case "sesames":
                    formattedRestrictions.add("sesamefree");
                    break;
                case "lupin":
                    formattedRestrictions.add("lupinefree");
                    break;
                case "judaisme":
                case "kacher":
                case "cacher":
                case "kasher":
                case "casher":
                    formattedRestrictions.add("judaism");
                    break;
                case "islam":
                case "halal":
                case "halel":
                    formattedRestrictions.add("islam");
                    break;
                case "lait":
                case "produits laitiers":
                    formattedRestrictions.add("dairyfree");
                    break;
            
                default:
                formattedRestrictions.add(normalizedStr);
                    break;
            }
        }

        return formattedRestrictions;
    }
}
