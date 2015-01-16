/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.PayGrade;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class PayGradeFacade extends AbstractFacade<PayGrade> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PayGradeFacade() {
        super(PayGrade.class);
    }

    public void addPayGrade(String name, float min, float max, 
            String currency) throws EJBException {
        em.persist(new PayGrade(name, min, max, currency));
    }
  
    public void editPayGrade(String oldId, String name, float min, float max, 
            String currency) throws EJBException {
        em.createQuery("UPDATE PayGrade p SET p.name = '" + name 
                + "', p.minimum = '" + min 
                + "', p.maximum = '" + max
                + "', p.currency = '" + currency 
                + "' WHERE p.name = '" + oldId + "'")
                .executeUpdate();
//        PayGrade find = em.find(PayGrade.class, oldId);
//        find.setName(name);
//        find.setMinimum(min);
//        find.setMaximum(max);
//        find.setCurrency(currency);
//        em.merge(find);
    }
    
    public void deletePayGrade(String id){
        em.remove(em.find(PayGrade.class, id));
    }
}
