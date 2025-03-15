package com.jlgdev.ceres.models.dataAccessObject;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("ingredient")
public class IngredientDAO {

    private Double quantity;

    private String nameFromApi;

    private AlimentDAO aliment;

    private String unit;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public AlimentDAO getAliment() {
        return aliment;
    }

    public void setAliment(AlimentDAO aliment) {
        this.aliment = aliment;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNameFromApi() {
        return nameFromApi;
    }

    public void setNameFromApi(String nameFromApi) {
        this.nameFromApi = nameFromApi;
    }

    public IngredientDAO() {
    }

    public IngredientDAO(Double quantity, String nameFromApi, AlimentDAO aliment, String unit) {
        this.quantity = quantity;
        this.nameFromApi = nameFromApi;
        this.aliment = aliment;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "IngredientDAO [quantity=" + quantity + ", nameFromApi=" + nameFromApi + ", aliment=" + aliment
                + ", unit=" + unit + "]";
    }

}
