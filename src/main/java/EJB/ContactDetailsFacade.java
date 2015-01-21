/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.ContactDetails;
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
    
    public void editContactDetails(String id, String country, String region, String city,
            String street, String mobileNumber, String homePhone,
            String workPhone, String email) {
        ContactDetails contactDetails = em.find(ContactDetails.class, id);
        contactDetails.setCountry(country);
        contactDetails.setRegion(region);
        contactDetails.setCity(city);
        contactDetails.setStreetAddress(street);
        contactDetails.setMobileNumber(mobileNumber);
        contactDetails.setHomeNumber(homePhone);
        contactDetails.setWorkNumber(workPhone);
        contactDetails.setEmail(email);
        em.merge(contactDetails);
    }
    
    public ContactDetails getContactDetails(String id) {
        return em.find(ContactDetails.class, id);        
    }
}
