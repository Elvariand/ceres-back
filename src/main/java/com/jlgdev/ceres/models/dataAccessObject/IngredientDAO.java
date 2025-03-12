package com.jlgdev.ceres.models.dataAccessObject;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("ingredient")
public class IngredientDAO {

    private Double quantity;

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

    public IngredientDAO() {
    }

    public IngredientDAO(Double quantity, AlimentDAO aliment, String unit) {
        this.quantity = quantity;
        this.aliment = aliment;
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
        IngredientDAO other = (IngredientDAO) obj;
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
