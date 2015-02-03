/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 *
 * @author Harvey
 */
@Entity
public class EmployeeImage implements Serializable {

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @Id
    @Column(name = "WORKERID")
    private String id;
    @Lob
    @Column(nullable = true)
    private byte[] image;
    @Column(name = "IMAGE_NAME", nullable = true)
    private String imageName;
    @Column(name = "IMAGE_TYPE", nullable = true)
    private String imageType;

    public EmployeeImage() {
    }

    public EmployeeImage(String id) {
        this.id = id;
    }
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     RELATIONAL MAPPING
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @JoinColumn(name="WORKERID",insertable=false,updatable=false, nullable = false, unique = true)
    @OneToOne(fetch = FetchType.EAGER)
    private Worker worker;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Worker getWorker() {
        return worker;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     GETTERS AND SETTERS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    public void setWorker(Worker worker) {
        this.worker = worker;
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
        if (!(object instanceof EmployeeImage)) {
            return false;
        }
        EmployeeImage other = (EmployeeImage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.EmployeeImages[ id=" + id + " ]";
    }

}
