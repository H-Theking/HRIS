 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Harvey
 */
@Entity
@IdClass(EmployeeSkillPK.class)
public class EmployeeSkill implements Serializable {

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @Id
    @Column(name = "SKILL")
    private String name;
    @Id
    @Column(name = "WORKERID")
    private String workerId;
    @Column(nullable = true)
    private int expYears;
    @Column(nullable = true)
    private String comment;

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     CONSTRUCTORS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    public EmployeeSkill() {
    }

    public EmployeeSkill(String name, String workerId) {
        this.name = name;
        this.workerId = workerId;
    }
    
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     ENTITY RELATIONSHIPS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @JoinColumn(name = "SKILL", insertable = false, updatable = false)
    @OneToOne(orphanRemoval = true)
    private Skill skill;

    @JoinColumn(name = "WORKERID", insertable = false, updatable = false)
    @ManyToOne
    private Worker worker;

    public String getName() {
        return name;
    }
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     GETTERS AND SETTERS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public int getExpYears() {
        return expYears;
    }

    public void setExpYears(int expYears) {
        this.expYears = expYears;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.workerId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeSkill other = (EmployeeSkill) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.workerId, other.workerId);
    }

}
