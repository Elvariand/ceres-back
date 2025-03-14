package com.jlgdev.ceres.models.dataAccessObject;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("MissingIngredient")
public class MissingIngredients {

    private int id;
    
    private Set<String> missingSet;

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

    public MissingIngredients(int id, Set<String> missingSet, int total) {
        this.id = 1;
        this.missingSet = missingSet;
        this.total = total;
    }

    public MissingIngredients() {
    }

}
