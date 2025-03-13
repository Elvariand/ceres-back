package com.jlgdev.ceres.models.dataAccessObject;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "nutrient")
public class NutrientDAO{

    @Indexed(direction = IndexDirection.ASCENDING)
    private String nameEn;
    
    private String nameFr;

    private Double amount;

    private String unit;

    private Double percentOfDailyNeed;



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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        if (unit.charAt(0) == 'Â') {
            unit.replace("Â", "");
        }
        this.unit = unit;
    }

    public Double getPercentOfDailyNeed() {
        return percentOfDailyNeed;
    }

    public void setPercentOfDailyNeed(Double percentOfDailyNeed) {
        this.percentOfDailyNeed = percentOfDailyNeed;
    }

    public NutrientDAO() {
    }

    public NutrientDAO(String nameEn, String nameFr, Double amount, String unit, Double percentOfDailyNeed) {
        this.nameEn = nameEn;
        this.nameFr = nameFr;
        this.amount = amount;
        this.unit = unit;
        this.percentOfDailyNeed = percentOfDailyNeed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nameEn == null) ? 0 : nameEn.hashCode());
        result = prime * result + ((nameFr == null) ? 0 : nameFr.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        result = prime * result + ((percentOfDailyNeed == null) ? 0 : percentOfDailyNeed.hashCode());
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
        NutrientDAO other = (NutrientDAO) obj;
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
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (unit == null) {
            if (other.unit != null)
                return false;
        } else if (!unit.equals(other.unit))
            return false;
        if (percentOfDailyNeed == null) {
            if (other.percentOfDailyNeed != null)
                return false;
        } else if (!percentOfDailyNeed.equals(other.percentOfDailyNeed))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "NutrientDAO [nameEn=" + nameEn + ", nameFr=" + nameFr + ", amount=" + amount + ", unit=" + unit
                + ", percentOfDailyNeed=" + percentOfDailyNeed + "]";
    }
}
