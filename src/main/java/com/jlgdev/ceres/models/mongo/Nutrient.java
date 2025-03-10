package com.jlgdev.ceres.models.mongo;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "nutrient")
public class Nutrient {

    @Indexed(direction = IndexDirection.ASCENDING)
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("amount")
    private Double amount;
    
    @JsonProperty("unit")
    private String unit;
    
    @JsonProperty("percentOfDailyNeeds")
    private Double percentOfDailyNeed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Double getPercentOfDailyNeed() {
        return percentOfDailyNeed;
    }

    public void setPercentOfDailyNeed(Double percentOfDailyNeed) {
        this.percentOfDailyNeed = percentOfDailyNeed;
    }

    public Nutrient() {
    }

    public Nutrient(String name, Double amount, String unit, Double percentOfDailyNeed) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.percentOfDailyNeed = percentOfDailyNeed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        result = prime * result + ((percentOfDailyNeed == null) ? 0 : percentOfDailyNeed.hashCode());
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
        Nutrient other = (Nutrient) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
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
        if (percentOfDailyNeed == null) {
            if (other.percentOfDailyNeed != null)
                return false;
        } else if (!percentOfDailyNeed.equals(other.percentOfDailyNeed))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Nutrient [name=" + name + ", amount=" + amount + ", unit=" + unit
                + ", percentOfDailyNeed=" + percentOfDailyNeed + "]";
    }

    
}
