package com.jlgdev.ceres.models.dataAccessObject;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("allergy")
public class AllergyDAO {

    @Id
    private String id;

    private String name;

    private Set<String> warnings;

    private Set<String> forbidden;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(Set<String> warnings) {
        this.warnings = warnings;
    }

    public Set<String> getForbidden() {
        return forbidden;
    }

    public void setForbidden(Set<String> forbidden) {
        this.forbidden = forbidden;
    }

    public AllergyDAO() {
    }

    @Override
    public String toString() {
        return "AllergyDAO [id=" + id + ", name=" + name + ", warnings=" + warnings + ", forbidden=" + forbidden + "]";
    }

}