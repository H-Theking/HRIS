/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Leave;
import Entities.Worker;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class LeaveFacade extends AbstractFacade<Leave> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LeaveFacade() {
        super(Leave.class);
    }
    
    public void addLeave(Worker worker, String type, Date start, Date end){
        Leave leave = new Leave(type, start, end);
        leave.setWorker(worker);
        em.persist(leave);
    }
    
    public void editLeave(Long leaveId, Worker worker, String type, Date start, Date end){
        Leave find = em.find(Leave.class, leaveId);
        find.setStartDate(start);
        find.setEndDate(end);
    }
}
