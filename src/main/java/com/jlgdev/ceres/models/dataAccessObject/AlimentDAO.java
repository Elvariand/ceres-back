package com.jlgdev.ceres.models.dataAccessObject;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "aliment")
public class AlimentDAO {

    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String nameEn;

    private String nameFr;

    private String aisle;

    private String image;

    private NutritionDAO nutrition;

    private Set<String> categoryPath;

    private Set<String> categoryPathEn;

    private Set<String> categoryPathFr;

    private Set<String> possibleUnits;

    private Double convertionValue;

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

    public NutritionDAO getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutritionDAO nutrition) {
        this.nutrition = nutrition;
    }

    public Set<String> getCategoryPath() {
        return categoryPath;
    }

    public Set<String> getCategoryPathEn() {
        return categoryPathEn;
    }

    public void setCategoryPathEn(Set<String> categoryPathEn) {
        this.categoryPathEn = categoryPathEn;
    }

    public Set<String> getCategoryPathFr() {
        return categoryPathFr;
    }

    public void setCategoryPathFr(Set<String> categoryPathFr) {
        this.categoryPathFr = categoryPathFr;
    }
    public void setCategoryPath(Set<String> categoryPath) {
        this.categoryPath = categoryPath;
    }
    
    public Set<String> getPossibleUnits() {
        return possibleUnits;
    }
    
    public void setPossibleUnits(Set<String> possibleUnits) {
        this.possibleUnits = possibleUnits;
    }

    public Double getConvertionValue() {
        return convertionValue;
    }

    public void setConvertionValue(Double convertionValue) {
        this.convertionValue = convertionValue;
    }

    public AlimentDAO() {
    }

    public AlimentDAO(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AlimentDAO [id=" + id + ", nameEn=" + nameEn + ", nameFr=" + nameFr + ", aisle=" + aisle + ", image="
                + image + ", nutrition=" + nutrition + ", categoryPath=" + categoryPath + ", categoryPathEn="
                + categoryPathEn + ", categoryPathFr=" + categoryPathFr + ", possibleUnits=" + possibleUnits
                + ", convertionValue=" + convertionValue + "]";
    }

}
