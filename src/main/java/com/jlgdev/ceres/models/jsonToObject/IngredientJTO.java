package com.jlgdev.ceres.models.jsonToObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientJTO {

    @JsonProperty("amount")
    private Double quantity;

    @JsonProperty("name")
    private String nameFromApi;

    @JsonProperty("id")
    private String idAliment;

    @JsonProperty("unit")
    private String unit;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getNameFromApi() {
        return nameFromApi;
    }

    public void setNameFromApi(String nameFromApi) {
        this.nameFromApi = nameFromApi;
    }

    public String getIdAliment() {
        return idAliment;
    }

    public void setIdAliment(String idAliment) {
        this.idAliment = idAliment;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public IngredientJTO(Double quantity, String nameFromApi, String idAliment, String unit) {
        this.quantity = quantity;
        this.nameFromApi = nameFromApi;
        this.idAliment = idAliment;
        this.unit = unit;
    }

    public IngredientJTO() {
    }

    @Override
    public String toString() {
        return "IngredientJTO [quantity=" + quantity + ", nameFromApi=" + nameFromApi + ", idAliment=" + idAliment
                + ", unit=" + unit + "]";
    }

}
