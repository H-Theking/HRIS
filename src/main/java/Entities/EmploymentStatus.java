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
public class EmploymentStatus implements Serializable {
    @Id
    private String status;

    public EmploymentStatus(String status) {
        this.status = status;
    }

    public EmploymentStatus() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (status != null ? status.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the status fields are not set
        if (!(object instanceof EmploymentStatus)) {
            return false;
        }
        EmploymentStatus other = (EmploymentStatus) object;
        if ((this.status == null && other.status != null) || (this.status != null && !this.status.equals(other.status))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.EmploymentSatus[ id=" + status + " ]";
    }
    
}
