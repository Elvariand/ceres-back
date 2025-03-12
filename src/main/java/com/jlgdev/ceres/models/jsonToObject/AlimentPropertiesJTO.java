package com.jlgdev.ceres.models.jsonToObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlimentPropertiesJTO {

    @JsonProperty("name")
    private String name;
    
    @JsonProperty("amount")
    private Double amount;


    
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

    public AlimentPropertiesJTO() {
    }

    public AlimentPropertiesJTO(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
        AlimentPropertiesJTO other = (AlimentPropertiesJTO) obj;
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
        return true;
    }

    @Override
    public String toString() {
        return "AlimentProperties [name=" + name + ", amount=" + amount + "]";
    }

    
}
