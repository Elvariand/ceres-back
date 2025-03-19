package com.jlgdev.ceres.models.dataAccessObject;

import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("MissingIngredient")
public class MissingIngredients {

    private int id;
    
    private Set<String> missingSet;
    
    private Map<String, Integer> missingMap;

    private int total;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<String> getMissingSet() {
        return missingSet;
    }

    public void setMissingSet(Set<String> missingSet) {
        this.missingSet = missingSet;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<String, Integer> getMissingMap() {
        return missingMap;
    }

    public void setMissingMap(Map<String, Integer> missingMap) {
        this.missingMap = missingMap;
    }

    public MissingIngredients(int id, Set<String> missingSet, int total) {
        this.id = 1;
        this.missingSet = missingSet;
        this.total = total;
    }

    public MissingIngredients() {
    }

    public MissingIngredients(int id, Set<String> missingSet, Map<String, Integer> missingMap, int total) {
        this.id = id;
        this.missingSet = missingSet;
        this.missingMap = missingMap;
        this.total = total;
    }
}
