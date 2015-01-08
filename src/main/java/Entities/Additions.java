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
public class Additions implements Serializable {

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @Id
    private String code;
    private String name;
    private Float amount;
    private Float base;
    private Float lowerBound;//lower bound of the basic salary to include
    private Float upperBound;//upper bound of the basic salary to include
    private Float maximum;
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     RELATIONAL MAPPING
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     CONSTRUCTORS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    public Additions() {
    }

    public Additions(String code, String name, Float amount, Float maximum) {
        this.code = code;
        this.name = name;
        this.amount = amount;
        this.maximum = maximum;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the code fields are not set
        if (!(object instanceof Additions)) {
            return false;
        }
        Additions other = (Additions) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Additions[ id=" + code + " ]";
    }

}
