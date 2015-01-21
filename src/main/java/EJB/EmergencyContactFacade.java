/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

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
public class EmergencyContactFacade extends AbstractFacade<EmergencyContact> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmergencyContactFacade() {
        super(EmergencyContact.class);
    }
    
    public void addEmergencyContact(String workerId, String name, String relation, 
            String mobileNumber, String homePhone, String workPhone){
        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setName(name);
        emergencyContact.setRelationship(relation);
        emergencyContact.setWorkNumber(workPhone);
        emergencyContact.setMobileNumber(mobileNumber);
        emergencyContact.setHomeNumber(homePhone);
        emergencyContact.setWorker(em.find(Worker.class, workerId));
        em.persist(emergencyContact);
    }
    
    public void editEmergencyContact(Long id, String name, String relation, 
            String mobileNumber, String homePhone, String workPhone){
        EmergencyContact emergencyContact = em.find(EmergencyContact.class, id);
        emergencyContact.setName(name);
        emergencyContact.setRelationship(relation);
        emergencyContact.setWorkNumber(workPhone);
        emergencyContact.setMobileNumber(mobileNumber);
        emergencyContact.setHomeNumber(homePhone);
        em.merge(emergencyContact);
    }
    
//    public EmergencyContact findEC(Long id){
//        return em.find(EmergencyContact.class, id);
//    }
    
    public List<EmergencyContact> getEmergencyContacts(String employeeId) {
        List<EmergencyContact> ec = em.createQuery("SELECT w FROM EmergencyContact w"
                + " WHERE w.worker.id = :id").setParameter("id", employeeId).getResultList();
        return ec;
    }    
    
}
