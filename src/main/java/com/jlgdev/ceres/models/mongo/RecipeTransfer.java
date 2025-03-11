package com.jlgdev.ceres.models.mongo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipeTransfer {

    @JsonProperty("results")
    private List<Recipe> results;

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((results == null) ? 0 : results.hashCode());
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
        RecipeTransfer other = (RecipeTransfer) obj;
        if (results == null) {
            if (other.results != null)
                return false;
        } else if (!results.equals(other.results))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RecipeTransfer [results=" + results + "]";
    }

    public RecipeTransfer() {
    }

    public RecipeTransfer(List<Recipe> results) {
        this.results = results;
    }
}
