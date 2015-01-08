/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.ContactDetails;
import Entities.EmergencyContact;
import Entities.Worker;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class ContactDetailsFacade extends AbstractFacade<ContactDetails> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactDetailsFacade() {
        super(ContactDetails.class);
    }
    
    public List<EmergencyContact> getEmergencyContact(String id){
        Worker worker =  em.find(Worker.class, id);
        List<EmergencyContact> ec = em.createQuery("SELECT w FROM EmergencyContact w"
                + " WHERE w.worker.id = :id").setParameter("id", id).getResultList();
        return ec;
    }
    
}
