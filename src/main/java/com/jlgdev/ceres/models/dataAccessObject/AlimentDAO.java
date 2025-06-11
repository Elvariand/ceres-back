package com.jlgdev.ceres.models.dataAccessObject;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "aliment")
public class AlimentDAO {
    public static final byte OK = 1;
    public static final byte WARNING = 2;
    public static final byte FORBIDDEN = 0;

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
    
    private byte vegetarian;

    private byte vegan;

    private byte arachidfree;

    private byte glutenfree;

    private byte eggfree;

    private byte nutfree;

    private byte shellfishfree;

    private byte seefoodfree;

    private byte mustardfree;

    private byte fishfree;

    private byte celeryfree;

    private byte soyfree;

    private byte sulfitfree;

    private byte sesamefree;

    private byte lupinefree;

    private byte judaism;

    private byte islam;

    private byte seasonal;
    
    private byte dairyfree;



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

    public byte getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(byte vegetarian) {
        this.vegetarian = vegetarian;
    }

    public byte getVegan() {
        return vegan;
    }

    public void setVegan(byte vegan) {
        this.vegan = vegan;
    }

    public byte getArachidfree() {
        return arachidfree;
    }

    public void setArachidfree(byte arachidfree) {
        this.arachidfree = arachidfree;
    }

    public byte getGlutenfree() {
        return glutenfree;
    }

    public void setGlutenfree(byte glutenfree) {
        this.glutenfree = glutenfree;
    }

    public byte getEggfree() {
        return eggfree;
    }

    public void setEggfree(byte eggfree) {
        this.eggfree = eggfree;
    }

    public byte getNutfree() {
        return nutfree;
    }

    public void setNutfree(byte nutfree) {
        this.nutfree = nutfree;
    }

    public byte getShellfishfree() {
        return shellfishfree;
    }

    public void setShellfishfree(byte shellfishfree) {
        this.shellfishfree = shellfishfree;
    }

    public byte getSeefoodfree() {
        return seefoodfree;
    }

    public void setSeefoodfree(byte seefoodfree) {
        this.seefoodfree = seefoodfree;
    }

    public byte getMustardfree() {
        return mustardfree;
    }

    public void setMustardfree(byte mustardfree) {
        this.mustardfree = mustardfree;
    }

    public byte getFishfree() {
        return fishfree;
    }

    public void setFishfree(byte fishfree) {
        this.fishfree = fishfree;
    }

    public byte getCeleryfree() {
        return celeryfree;
    }

    public void setCeleryfree(byte celeryfree) {
        this.celeryfree = celeryfree;
    }

    public byte getSoyfree() {
        return soyfree;
    }

    public void setSoyfree(byte soyfree) {
        this.soyfree = soyfree;
    }

    public byte getSulfitfree() {
        return sulfitfree;
    }

    public void setSulfitfree(byte sulfitfree) {
        this.sulfitfree = sulfitfree;
    }

    public byte getSesamefree() {
        return sesamefree;
    }

    public void setSesamefree(byte sesamefree) {
        this.sesamefree = sesamefree;
    }

    public byte getLupinefree() {
        return lupinefree;
    }

    public void setLupinefree(byte lupinefree) {
        this.lupinefree = lupinefree;
    }

    public byte getJudaism() {
        return judaism;
    }

    public void setJudaism(byte judaism) {
        this.judaism = judaism;
    }

    public byte getIslam() {
        return islam;
    }

    public void setIslam(byte islam) {
        this.islam = islam;
    }

    public byte getSeasonal() {
        return seasonal;
    }

    public void setSeasonal(byte seasonal) {
        this.seasonal = seasonal;
    }

    public byte getDairyfree() {
        return dairyfree;
    }

    public void setDairyfree(byte dairyfree) {
        this.dairyfree = dairyfree;
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
                + ", convertionValue=" + convertionValue + ", vegetarian=" + vegetarian + ", vegan=" + vegan
                + ", arachidfree=" + arachidfree + ", glutenfree=" + glutenfree + ", eggfree=" + eggfree + ", nutfree="
                + nutfree + ", shellfishfree=" + shellfishfree + ", seefoodfree=" + seefoodfree + ", mustardfree="
                + mustardfree + ", fishfree=" + fishfree + ", celeryfree=" + celeryfree + ", soyfree=" + soyfree
                + ", sulfitfree=" + sulfitfree + ", sesamefree=" + sesamefree + ", lupinefree=" + lupinefree
                + ", judaism=" + judaism + ", islam=" + islam + ", seasonal=" + seasonal + ", dairyfree=" + dairyfree
                + "]";
    }

}
