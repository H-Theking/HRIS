/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.TerminationReason;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class TerminationReasonFacade extends AbstractFacade<TerminationReason> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TerminationReasonFacade() {
        super(TerminationReason.class);
    }
    
    public void addReason(String reason){
        em.persist(new TerminationReason(reason));
    }
    
    public void editReason(Long id, String reason){
        TerminationReason find = em.find(TerminationReason.class, id);
        find.setReason(reason);
        em.merge(find);
    }
    
}
