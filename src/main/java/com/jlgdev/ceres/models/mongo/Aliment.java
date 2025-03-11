package com.jlgdev.ceres.models.mongo;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "aliment")
public class Aliment {

    @JsonProperty("id")
    @Id
    private String id;

    @JsonProperty("name")
    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String nameEn;

    private String nameFr;

    @JsonProperty("aisle")
    private String aisle;

    @JsonProperty("image")
    private String image;

    @JsonProperty("nutrition")
    private Nutrition nutrition;

    @JsonProperty("categoryPath")
    private Set<String> categoryPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameFr() {
        return nameFr;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public Set<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(Set<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public Aliment() {
    }

    public Aliment(String id, String nameEn, String nameFr, String aisle, String image, Nutrition nutrition,
            Set<String> categoryPath) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameFr = nameFr;
        this.aisle = aisle;
        this.image = image;
        this.nutrition = nutrition;
        this.categoryPath = categoryPath;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nameEn == null) ? 0 : nameEn.hashCode());
        result = prime * result + ((nameFr == null) ? 0 : nameFr.hashCode());
        result = prime * result + ((aisle == null) ? 0 : aisle.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((nutrition == null) ? 0 : nutrition.hashCode());
        result = prime * result + ((categoryPath == null) ? 0 : categoryPath.hashCode());
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
        Aliment other = (Aliment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nameEn == null) {
            if (other.nameEn != null)
                return false;
        } else if (!nameEn.equals(other.nameEn))
            return false;
        if (nameFr == null) {
            if (other.nameFr != null)
                return false;
        } else if (!nameFr.equals(other.nameFr))
            return false;
        if (aisle == null) {
            if (other.aisle != null)
                return false;
        } else if (!aisle.equals(other.aisle))
            return false;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (nutrition == null) {
            if (other.nutrition != null)
                return false;
        } else if (!nutrition.equals(other.nutrition))
            return false;
        if (categoryPath == null) {
            if (other.categoryPath != null)
                return false;
        } else if (!categoryPath.equals(other.categoryPath))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Aliment [id=" + id + ", nameEn=" + nameEn + ", nameFr=" + nameFr + ", aisle=" + aisle + ", image="
                + image + ", nutrition=" + nutrition + ", categoryPath=" + categoryPath + "]";
    }
}
