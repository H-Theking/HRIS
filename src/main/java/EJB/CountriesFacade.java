/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Countries;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class CountriesFacade extends AbstractFacade<Countries> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CountriesFacade() {
        super(Countries.class);
    }
    
    public void addCountry(String country, String nationality){
        em.persist(new Countries(country, nationality));
    }
    
    public void editcountry(Long id, String country, String nationality){
        Countries find = em.find(Countries.class, id);
        find.setCountry(country);
        find.setNationality(nationality);
        em.merge(find);
    }
}
