package com.jlgdev.ceres.models.jsonToObject;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NutritionJTO {

    @JsonProperty("nutrients")
    private Set<NutrientJTO> nutrients;

    @JsonProperty("properties")
    private Set<AlimentPropertiesJTO> properties;

    @JsonProperty("flavonoids")
    private Set<FlavonoidJTO> flavonoids;

    @JsonProperty("caloricBreakdown")
    private Map<String, Double> caloricBreakdown;

    @JsonProperty("weightPerServing")
    private WeightPerServingJTO weightPerServing;



    public Set<NutrientJTO> getNutrients() {
        return nutrients;
    }

    public void setNutrients(Set<NutrientJTO> nutrients) {
        this.nutrients = nutrients;
    }

    public void addNutrients(NutrientJTO nutrientToAdd) {
        this.nutrients.add(nutrientToAdd);
    }

    public Set<AlimentPropertiesJTO> getProperties() {
        return properties;
    }

    public void setProperties(Set<AlimentPropertiesJTO> properties) {
        this.properties = properties;
    }

    public void addProperties(AlimentPropertiesJTO propertyToAdd) {
        this.properties.add(propertyToAdd);
    }

    public Set<FlavonoidJTO> getFlavonoids() {
        return flavonoids;
    }

    public void setFlavonoids(Set<FlavonoidJTO> flavonoids) {
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
    
    public WeightPerServingJTO getWeightPerServing() {
        return weightPerServing;
    }

    public void setWeightPerServing(WeightPerServingJTO weightPerServing) {
        this.weightPerServing = weightPerServing;
    }

    public NutritionJTO() {
    }

    public NutritionJTO(Set<NutrientJTO> nutrients, Set<AlimentPropertiesJTO> properties, Set<FlavonoidJTO> flavonoids,
            Map<String, Double> caloricBreakdown, WeightPerServingJTO weightPerServing) {
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
        NutritionJTO other = (NutritionJTO) obj;
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
