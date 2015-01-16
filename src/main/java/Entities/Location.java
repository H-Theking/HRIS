/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Harvey
 */
@Entity
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long branchId;
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
  
    public Location() {
    }

    public Location(String name, String country, String region, String city, String street, String telepone, String fax, String email) {
        this.name = name;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.telepone = telepone;
        this.fax = fax;
        this.email = email;
    }
    /*----------------------------------------------------------------
    ----------------------------------------------------------------
    GETTERS AND SETTERS
    ----------------------------------------------------------------
    ----------------------------------------------------------------*/
    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (branchId != null ? branchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the branchId fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.branchId == null && other.branchId != null) || (this.branchId != null && !this.branchId.equals(other.branchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Location[ id=" + branchId + " ]";
    }

}
