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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Harvey
 */
@Entity
@NamedQueries({
    @NamedQuery(name="findAllJobs", query = "SELECT j from Job j")
    })
public class Job implements Serializable {
    
    @Id
    @Column(name = "JOBTITLE")
    private String jobTitle;
    @Column(nullable = true)
    private String jobDescription;
    @Column(nullable = true)
    private String note;

    public Job() {
    }

    public Job(String jobTitle, String jobDescription, String note) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.note = note;
    }


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobTitle != null ? jobTitle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the jobTitle fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.jobTitle == null && other.jobTitle != null) || (this.jobTitle != null && !this.jobTitle.equals(other.jobTitle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Job[ jobTitle=" + jobTitle + " ]";
    }
    
}
