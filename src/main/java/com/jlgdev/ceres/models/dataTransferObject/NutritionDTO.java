package com.jlgdev.ceres.models.dataTransferObject;

import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "nutrition")
public class NutritionDTO {

    private Set<NutrientDTO> nutrients;

    private Set<AlimentPropertiesDTO> properties;

    private Set<FlavonoidDTO> flavonoids;

    private Map<String, Double> caloricBreakdown;

    private WeightPerServingDTO weightPerServing;



    public Set<NutrientDTO> getNutrients() {
        return nutrients;
    }

    public void setNutrients(Set<NutrientDTO> nutrients) {
        this.nutrients = nutrients;
    }

    public void addNutrients(NutrientDTO nutrientToAdd) {
        this.nutrients.add(nutrientToAdd);
    }

    public Set<AlimentPropertiesDTO> getProperties() {
        return properties;
    }

    public void setProperties(Set<AlimentPropertiesDTO> properties) {
        this.properties = properties;
    }

    public void addProperties(AlimentPropertiesDTO propertyToAdd) {
        this.properties.add(propertyToAdd);
    }

    public Set<FlavonoidDTO> getFlavonoids() {
        return flavonoids;
    }

    public void setFlavonoids(Set<FlavonoidDTO> flavonoids) {
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
    
    public WeightPerServingDTO getWeightPerServing() {
        return weightPerServing;
    }

    public void setWeightPerServing(WeightPerServingDTO weightPerServing) {
        this.weightPerServing = weightPerServing;
    }

    public NutritionDTO() {
    }

    public NutritionDTO(Set<NutrientDTO> nutrients, Set<AlimentPropertiesDTO> properties, Set<FlavonoidDTO> flavonoids,
            Map<String, Double> caloricBreakdown, WeightPerServingDTO weightPerServing) {
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
        NutritionDTO other = (NutritionDTO) obj;
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
