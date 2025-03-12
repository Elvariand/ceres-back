package com.jlgdev.ceres.models.jsonToObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientJTO {

    @JsonProperty("amount")
    private Double quantity;

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

    public IngredientJTO() {
    }

    public IngredientJTO(Double quantity, String id, String unit) {
        this.quantity = quantity;
        this.idAliment = id;
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((idAliment == null) ? 0 : idAliment.hashCode());
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
        IngredientJTO other = (IngredientJTO) obj;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (idAliment == null) {
            if (other.idAliment != null)
                return false;
        } else if (!idAliment.equals(other.idAliment))
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
        return "IngredientJTO [quantity=" + quantity + ", idAliment=" + idAliment + ", unit=" + unit + "]";
    }

}
