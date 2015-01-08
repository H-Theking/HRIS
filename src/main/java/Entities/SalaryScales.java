/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Harvey
 */
@Entity
public class SalaryScales implements Serializable {
    
    public enum Type{
        addition,
        deduction
    }
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    @Id
    private String code;
    private Type type;
    private String name;
    private Float lowerBound;//lower bound of the basic salary to include 
    private Float upperBound;//upper bound of the basic salary to include 
    private float amount;//either the base of a Type or the amount for a fixed
//    value component like overtime
    @Column(nullable = true)
    private float maximum;//valid for overtimes

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     RELATIONAL MAPPING
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

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
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getMaximum() {
        return maximum;
    }

    public void setMaximum(float maximum) {
        this.maximum = maximum;
    }

}
