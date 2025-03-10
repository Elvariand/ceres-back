package com.jlgdev.ceres.models.mongo;

import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "nutrition")
public class Nutrition {

    @JsonProperty("nutrients")
    private Set<Nutrient> nutrients;

    @JsonProperty("properties")
    private Set<AlimentProperties> properties;

    @JsonProperty("flavonoids")
    private Set<Flavonoid> flavonoids;

    @JsonProperty("caloricBreakdown")
    private Map<String, Double> caloricBreakdown;

    @JsonProperty("weightPerServing")
    private WeightPerServing weightPerServing;

    public Set<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(Set<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    public void addNutrients(Nutrient nutrientToAdd) {
        this.nutrients.add(nutrientToAdd);
    }

    public Set<AlimentProperties> getProperties() {
        return properties;
    }

    public void setProperties(Set<AlimentProperties> properties) {
        this.properties = properties;
    }

    public void addProperties(AlimentProperties propertyToAdd) {
        this.properties.add(propertyToAdd);
    }

    public Set<Flavonoid> getFlavonoids() {
        return flavonoids;
    }

    public void setFlavonoids(Set<Flavonoid> flavonoids) {
        this.flavonoids = flavonoids;
    }

    public Map<String, Double> getCaloricBreakdown() {
        return caloricBreakdown;
    }

    public void setCaloricBreakdown(Map<String, Double> caloricBreakdown) {
        this.caloricBreakdown = caloricBreakdown;
    }
    
    public void addCaloricBreakdown(String name, Double quantity) {
        this.caloricBreakdown.put(name, quantity);
    }
    
    public WeightPerServing getWeightPerServing() {
        return weightPerServing;
    }

    public void setWeightPerServing(WeightPerServing weightPerServing) {
        this.weightPerServing = weightPerServing;
    }

    public Nutrition() {
    }

    public Nutrition(Set<Nutrient> nutrients, Set<AlimentProperties> properties, Set<Flavonoid> flavonoids,
            Map<String, Double> caloricBreakdown, WeightPerServing weightPerServing) {
        this.nutrients = nutrients;
        this.properties = properties;
        this.flavonoids = flavonoids;
        this.caloricBreakdown = caloricBreakdown;
        this.weightPerServing = weightPerServing;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nutrients == null) ? 0 : nutrients.hashCode());
        result = prime * result + ((properties == null) ? 0 : properties.hashCode());
        result = prime * result + ((flavonoids == null) ? 0 : flavonoids.hashCode());
        result = prime * result + ((caloricBreakdown == null) ? 0 : caloricBreakdown.hashCode());
        result = prime * result + ((weightPerServing == null) ? 0 : weightPerServing.hashCode());
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
        Nutrition other = (Nutrition) obj;
        if (nutrients == null) {
            if (other.nutrients != null)
                return false;
        } else if (!nutrients.equals(other.nutrients))
            return false;
        if (properties == null) {
            if (other.properties != null)
                return false;
        } else if (!properties.equals(other.properties))
            return false;
        if (flavonoids == null) {
            if (other.flavonoids != null)
                return false;
        } else if (!flavonoids.equals(other.flavonoids))
            return false;
        if (caloricBreakdown == null) {
            if (other.caloricBreakdown != null)
                return false;
        } else if (!caloricBreakdown.equals(other.caloricBreakdown))
            return false;
        if (weightPerServing == null) {
            if (other.weightPerServing != null)
                return false;
        } else if (!weightPerServing.equals(other.weightPerServing))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Nutrition [nutrients=" + nutrients + ", properties=" + properties + ", flavonoids="
                + flavonoids + ", caloricBreakdown=" + caloricBreakdown + ", weightPerServing=" + weightPerServing
                + "]";
    }

    
}
