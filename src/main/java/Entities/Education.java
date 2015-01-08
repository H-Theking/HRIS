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
public class Education implements Serializable {
    
    @Id
    private String level;

    public Education() {
    }
    

    public Education(String level) {
        this.level = level;
    }
    

    public String getLevel() {
        return level;
    }
    
    public void setLevel(String Level) {
        this.level = Level;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (level != null ? level.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the level fields are not set
        if (!(object instanceof Education)) {
            return false;
        }
        Education other = (Education) object;
        if ((this.level == null && other.level != null) || (this.level != null && !this.level.equals(other.level))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Harvey.HRMS.Entities.Eduction[ id=" + level + " ]";
    }
    
}
