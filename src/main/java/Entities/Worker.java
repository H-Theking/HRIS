/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.validation.constraints.Past;

/**
 *
 * @author Harvey
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findEmployees", query = "SELECT w FROM Worker w"),//dont forget to get on active status employees
    @NamedQuery(name = "findEmployeesByName", query = "SELECT w FROM Worker w "
            + "WHERE w.firstName LIKE :name OR"
            + " w.middlenames LIKE :name OR w.lastName LIKE :name"),
    @NamedQuery(name = "findEmployeesById", query = "SELECT w FROM Worker w "
            + "WHERE w.id = :id ")})
public class Worker implements Serializable {

    public enum CivilStatus {

        Célibataire,
        Marié
    }

    public enum Gender {

        Homme,
        Femme
    }
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    @Id
    @Column(name = "ID")
    private String id;
    private String firstName;
    @Column(nullable = true)
    private String middlenames;
    private String lastName;
    @Past
    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;
    @Column(nullable = true)
    private String placeOfBirth;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private CivilStatus civilStatus;
    @Column(nullable = true)
    private String nationality;
    @Column(nullable = true)
    private int numberOfChildren;

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     CONSTRUCTORS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    public Worker() {
    }

    public Worker(String id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Worker(String id, String firstName, String middlenames, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.middlenames = middlenames;
        this.lastName = lastName;
    }
    
    

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     RELATIONAL MAPPING
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
            
//    @OneToMany
//    @JoinColumn
//    private List<EmployeeHasEducation> degrees;
    
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     GETTERS AND SETTERS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    @PrePersist
    public void prePersistCallBack() {
//        if (job != null) {
//          job.setEmployeeId(this.id);
//        }
//        if (contactDetails != null) {
//            contactDetails.setContactId(this.id);
//        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMiddlenames() {
        return middlenames;
    }

    public void setMiddlenames(String middlenames) {
        this.middlenames = middlenames;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public CivilStatus getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(CivilStatus civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Worker)) {
            return false;
        }
        Worker other = (Worker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Worker[ id=" + id + " ]";
    }

}
