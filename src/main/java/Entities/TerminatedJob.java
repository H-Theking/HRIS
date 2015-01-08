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
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Harvey
 */
@Entity
public class TerminatedJob implements Serializable {

    @Id
    private String id;
    private String reason;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @Column(nullable = true)
    private String note;

    public TerminatedJob() {
    }

    public TerminatedJob(String reason, Date date, String note) {
        this.reason = reason;
        this.date = date;
        this.note = note;
    }

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     RELATIONAL MAPPING
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    
    @OneToOne
    @MapsId("employeeId")
    private EmployeeHasJob job;
    
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     GETTERS AND SETTERS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(object instanceof TerminatedJob)) {
            return false;
        }
        TerminatedJob other = (TerminatedJob) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TerminatedJob[ id=" + id + " ]";
    }

}
