/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Harvey
 */
@Entity
@NamedQuery(name = "getEmployeeData", query = "SELECT j FROM EmployeeHasJob j")
public class EmployeeHasJob implements Serializable {

    public enum Status {

        Enabled, Disabled
    }

    public enum Contract {

        Actif, Resilie
    }
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @Id
    private String employeeId;
    private String jobTitle;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = true)
    private Calendar startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = true)
    private Calendar endDate;
    @Column(nullable = true)
    private String contractDetails;
    @Column(nullable = true)
    private String employmentStatus;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = true)
    private String departmentName;
    @Column(nullable = true)
    private String subUnit;
    private Contract contractStatus;

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     RELATIONAL MAPPING
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "DEPARTMENT", referencedColumnName = "DEPARTMENTNAME",
            insertable = false, updatable = false)
    private Department department;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "JOBTITLE", referencedColumnName = "JOBTITLE",
            insertable = false, updatable = false)
    private Job job;
    
    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="EMPLOYEEID",insertable=false,updatable=false)
    private Worker worker;
    
    @OneToOne(orphanRemoval = true)
    @JoinColumn(insertable = false, updatable = false)
    private Location location;

    /*---------------------------------------------------------------
     ----------------------------------------------------------------
     CONSTRUCTORS
     ----------------------------------------------------------------
     ----------------------------------------------------------------
     -------*/
    public EmployeeHasJob() {
    }

    public EmployeeHasJob(String employeeId) {
        this.employeeId = employeeId;
        this.setStatus(Status.Disabled);
    }

    /*---------------------------------------------------------
     ----------------------------------------------------------------
     GETTERS AND SETTERS
     ----------------------------------------------------------------
     ----------------------------------------------------------------
     -------*/
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getContractDetails() {
        return contractDetails;
    }

    public void setContractDetails(String contractDetails) {
        this.contractDetails = contractDetails;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Status getStatus() {
        return status;
    }

    public final void setStatus(Status status) {
        this.status = status;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getSubUnit() {
        return subUnit;
    }

    public void setSubUnit(String subUnit) {
        this.subUnit = subUnit;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Contract getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Contract contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.employeeId);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the employeeId fields are not set
        if (!(object instanceof EmployeeHasJob)) {
            return false;
        }
        EmployeeHasJob other = (EmployeeHasJob) object;
        if (this.employeeId != other.employeeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Harvey.HRMS.Entities.Employee_Has_Job[ id=" + employeeId + " ]";
    }

}
