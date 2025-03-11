package com.jlgdev.ceres.models.mongo;

import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document("ingredient")
public class Ingredient {

    @JsonProperty("amount")
    private Double quantity;

    @JsonProperty("id")
    private String idAliment;

    private Aliment aliment;

    @JsonProperty("unit")
    private String unit;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Aliment getAliment() {
        return aliment;
    }

    public void setAliment(Aliment aliment) {
        this.aliment = aliment;
    }
    

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIdAliment() {
        return idAliment;
    }
    
    public void setIdAliment(String idAliment) {
        this.idAliment = idAliment;
    }

    public Ingredient() {
    }

    public Ingredient(Double quantity, Aliment aliment, String unit) {
        this.quantity = quantity;
        this.aliment = aliment;
        this.unit = unit;
        this.idAliment = aliment.getId();
    }

    public Ingredient(Double quantity, String id, String unit) {
        this.quantity = quantity;
        this.idAliment = aliment.getId();
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((aliment == null) ? 0 : aliment.hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
        Ingredient other = (Ingredient) obj;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (aliment == null) {
            if (other.aliment != null)
                return false;
        } else if (!aliment.equals(other.aliment))
            return false;
        if (unit == null) {
            if (other.unit != null)
                return false;
        } else if (!unit.equals(other.unit))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ingredient [quantity=" + quantity + ", aliment=" + aliment + ", unit=" + unit + "]";
    }


}
