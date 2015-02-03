/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.time.Year;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Past;

/**
 *
 * @author Harvey
 */
@Entity
public class EmployeeHasEducation implements Serializable {
    private static final long serialVersionUID = 1L;
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = true)
    private String institute;
    @Column(nullable = true)
    private String specialty;
    @Column(nullable = true)
    private Year gradYear;
    @Column(nullable = true)
    private String score;
    @Column(nullable = true) @Past 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Column(nullable = true) @Past 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     RELATIONAL MAPPING
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    
    @ManyToOne
    @JoinColumn(nullable = true)
    private Education degree;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Worker worker;
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     GETTERS AND SETTERS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    public EmployeeHasEducation() {
    }

    public EmployeeHasEducation(Education degree) {
        this.degree = degree;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public Year getGradYear() {
        return gradYear;
    }

    public void setGradYear(Year gradYear) {
        this.gradYear = gradYear;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Education getDegree() {
        return degree;
    }

    public void setDegree(Education degree) {
        this.degree = degree;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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
        if (!(object instanceof EmployeeHasEducation)) {
            return false;
        }
        EmployeeHasEducation other = (EmployeeHasEducation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.EmployeeHasEducation[ id=" + id + " ]";
    }
    
}
