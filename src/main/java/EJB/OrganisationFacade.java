/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Organisation;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class OrganisationFacade extends AbstractFacade<Organisation> {

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrganisationFacade() {
        super(Organisation.class);
    }

    public void editOrganisationDetails(String id, String companyName, String registrationNumber,
            int numberOfEmployees, String phoneNumber, String fax, String email,
            String country, String region, String city, String address) throws EJBException {
        Organisation find = em.find(Organisation.class, id);
        find.setName(companyName);
        find.setRegistrationNumber(registrationNumber);
        find.setCountry(country);
        find.setRegion(region);
        find.setCity(city);
        find.setStreet(address);
        find.setTelepone(phoneNumber);
        find.setFax(fax);
        find.setEmail(email);
        em.merge(find);
    }
}
