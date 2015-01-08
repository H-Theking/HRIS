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
import javax.persistence.NamedQuery;

/**
 *
 * @author Harvey
 */
@Entity
//@NamedQuery(name = "removeDepartment", query = "")
public class Department implements Serializable {

    @Id
    @Column(name = "DEPARTMENTNAME")
    private String name;
    @Column(nullable = true)
    private String subUnit;

    public Department() {
    }

    public Department(String name, String subUnit) {
        this.name = name;
        this.subUnit = subUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubUnit() {
        return subUnit;
    }

    public void setSubUnit(String subUnit) {
        this.subUnit = subUnit;
    }

    @Override
    public String toString() {
        return "javaeetutorial.order.entity.Department[ id=" + name + " ]";
    }

}
