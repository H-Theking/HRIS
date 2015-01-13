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

    public void editOrganisationDetails(String companyName, String registrationNumber, 
            int numberOfEmployees, String phoneNumber, String fax, String email, 
            String country, String region, String city, String address) throws EJBException {
        em.createQuery("UPDATE Organisation o SET o.name = '" + companyName
                + "', o.registrationNumber = " + registrationNumber
                + "', o.numberOfEmployees = " + numberOfEmployees
                + "', o.country = " + country
                + "', o.region = " + region
                + "', o.city = " + city
                + "', o.street = " + address
                + "', o.telepone = " + phoneNumber
                + "', o.fax = " + fax
                + "', o.email" + email);
    }
}
