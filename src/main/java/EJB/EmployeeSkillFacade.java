/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.EmployeeSkill;
import Entities.EmployeeSkillPK;
import Entities.Worker;
import Web.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class EmployeeSkillFacade extends AbstractFacade<EmployeeSkill> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeSkillFacade() {
        super(EmployeeSkill.class);
    }
    
    public void addSkill(String skill, String workerId, int years, String comment){ 
        EmployeeSkill employeeSkill = new EmployeeSkill(skill, workerId);
        employeeSkill.setComment(comment);
        employeeSkill.setExpYears(years);
        employeeSkill.setWorkerId(workerId);
//        employeeSkill.setWorker(em.find(Worker.class, workerId));
        em.persist(employeeSkill);
    }
    
    public void editSkill(EmployeeSkillPK pk, int years, String comment){
        EmployeeSkill employeeSkill = em.find(EmployeeSkill.class, pk);
        employeeSkill.setExpYears(years);
        employeeSkill.setComment(comment);
        em.merge(employeeSkill);
    }
}
