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
public class Organisation implements Serializable {
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @Id
    private String name;
    private String country;
    private String region;
    private String city;
    private String street;
    private String telepone;
    private String fax;
    private String email;

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
    public Organisation() {
    }

    public Organisation(String name) {
        this.name = name;
    }

    /*----------------------------------------------------------------
    ----------------------------------------------------------------
    GETTERS AND SETTERS
    ----------------------------------------------------------------
    ----------------------------------------------------------------*/
    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTelepone() {
        return telepone;
    }

    public void setTelepone(String telepone) {
        this.telepone = telepone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the Name fields are not set
        if (!(object instanceof Organisation)) {
            return false;
        }
        Organisation other = (Organisation) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Organisation[ id=" + name + " ]";
    }

}
