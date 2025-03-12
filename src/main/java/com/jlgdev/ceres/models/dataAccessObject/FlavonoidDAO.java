package com.jlgdev.ceres.models.dataAccessObject;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("flavonoids")
public class FlavonoidDAO {

    private String nameEn;

    private String nameFr;

    private Double amount;



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

    public FlavonoidDAO() {
    }

    public FlavonoidDAO(String nameEn, String nameFr, Double amount) {
        this.nameEn = nameEn;
        this.nameFr = nameFr;
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nameEn == null) ? 0 : nameEn.hashCode());
        result = prime * result + ((nameFr == null) ? 0 : nameFr.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
        FlavonoidDAO other = (FlavonoidDAO) obj;
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
        return true;
    }

    @Override
    public String toString() {
        return "FlavonoidDAO [nameEn=" + nameEn + ", nameFr=" + nameFr + ", amount=" + amount + "]";
    }

}
