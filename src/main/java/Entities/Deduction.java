/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Harvey
 */
@Entity
public class Deduction implements Serializable {

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @Id
    private String code;
    private String name;
    private Float wageRate;
    private Float bossRate;
    private Float base;
    private Float lowerBound;//lower bound of the basic salary to include
    private Float upperBound;//upper bound of the basic salary to include
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     RELATIONAL MAPPING
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    
    
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     CONTRUCTORS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    public Deduction() {
    }

    public Deduction(String code, String name, Float wageRate, Float bossRate) {
        this.code = code;
        this.name = name;
        this.wageRate = wageRate;
        this.bossRate = bossRate;
    }
    
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     GETTERS AND SETTERS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getWageRate() {
        return wageRate;
    }

    public void setWageRate(Float wageRate) {
        this.wageRate = wageRate;
    }

    public Float getBossRate() {
        return bossRate;
    }

    public void setBossRate(Float bossRate) {
        this.bossRate = bossRate;
    }

    public Float getBase() {
        return base;
    }

    public void setBase(Float base) {
        this.base = base;
    }

    public Float getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Float lowerBound) {
        this.lowerBound = lowerBound;
    }

    public Float getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Float upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the code fields are not set
        if (!(object instanceof Deduction)) {
            return false;
        }
        Deduction other = (Deduction) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Deductions[ id=" + code + " ]";
    }

}
