package com.jlgdev.ceres.models.dataTransferObject.NoSQL;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "weightPerServing")
public class WeightPerServingDTO {

    private Double amount;

    private String unit;



    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        if (unit.charAt(0) == 'Â') {
            unit.replace("Â", "");
        }
        this.unit = unit;
    }

    public WeightPerServingDTO() {
    }

    public WeightPerServingDTO(Double amount, String unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
        WeightPerServingDTO other = (WeightPerServingDTO) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
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
        return "WeightPerServing [amount=" + amount + ", unit=" + unit + "]";
    }

    
}
