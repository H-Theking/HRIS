/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Location;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class LocationFacade extends AbstractFacade<Location> {

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocationFacade() {
        super(Location.class);
    }

    public void addLocation(String name, String country, String region,
            String city, String address, String telephone,
            String fax, String email) throws EJBException {
        em.persist(new Location(name, country, region, city, address, telephone, fax, email));
    }
    
    public void editLocation(String former, String name, String country, String region,
            String city, String address, String telephone,
            String fax, String email){
        em.createQuery("UPDATE Location l SET"
                + "', l.name = '" + name
                + "', l.country  = '" + country
                + "', l.city = '" + city
                + "', l.street = '" + address
                + "', l.telepone = '" + telephone
                + "', l.fax = '" + fax
                + "', l.email = '" + email
                + "', WHERE l.name = '" + former + "'")
                .executeUpdate();
    }
}
