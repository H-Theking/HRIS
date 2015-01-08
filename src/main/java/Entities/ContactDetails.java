/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Harvey
 */
@Entity
public class ContactDetails implements Serializable {
    
     /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    @Id
    private String contactId;
    @Column(nullable = true)
    private String country;
    @Column(nullable = true)
    private String region;
    @Column(nullable = true)
    private String city;
    @Column(nullable = true)
    private String streetAddress;
    @Column(nullable = true)
    private String mobileNumber;
    @Column(nullable = true)
    private String homeNumber;
    @Column(nullable = true)
    private String workNumber;
    @Column(nullable = true)
    private String email;

    
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     RELATIONAL MAPPING
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    
    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="CONTACTID",insertable=false,updatable=false)
    private Worker worker;
    
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     CONSTRUCTORS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    public ContactDetails() {
    }
    
    public ContactDetails(String contactId) {
        this.contactId = contactId;
    }
    
    
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     GETTERS AND SETTERS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
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
        hash += (contactId != null ? contactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the contactId fields are not set
        if (!(object instanceof ContactDetails)) {
            return false;
        }
        ContactDetails other = (ContactDetails) object;
        if ((this.contactId == null && other.contactId != null) || (this.contactId != null && !this.contactId.equals(other.contactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EJB.ContactDetails[ id=" + contactId + " ]";
    }
    
}
