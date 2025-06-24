package com.jlgdev.ceres.models.dataTransferObject;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("allergy")
public class AllergyDTO {

    @Id
    private String id;

    private String name;

    private Set<String> warning;

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

    public Set<String> getWarning() {
        return warning;
    }

    public void setWarnings(Set<String> warning) {
        this.warning = warning;
    }

    public Set<String> getForbidden() {
        return forbidden;
    }

    public void setForbidden(Set<String> forbidden) {
        this.forbidden = forbidden;
    }

    public AllergyDTO() {
    }

    @Override
    public String toString() {
        return "AllergyDAO [id=" + id + ", name=" + name + ", warning=" + warning + ", forbidden=" + forbidden + "]";
    }

}