/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.EmploymentStatus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class EmploymentSatusFacade extends AbstractFacade<EmploymentStatus> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmploymentSatusFacade() {
        super(EmploymentStatus.class);
    }
    
    public void addStatus(String status){
        em.persist(new EmploymentStatus(status));
    }
    
    public void editStatus(String old, String newStatus){
        em.createQuery("UPDATE EmploymentStatus s SET s.status = '" + newStatus
        + "' WHERE s.status = '" + old + "'").executeUpdate();
//        EmploymentStatus find = em.find(EmploymentStatus.class, old);
//        find.setStatus(newStatus);
//        em.persist(find);
    }
}
