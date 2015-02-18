/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Abscence;
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
public class AbscenceFacade extends AbstractFacade<Abscence> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AbscenceFacade() {
        super(Abscence.class);
    }
    
    public void addAbscence(Worker worker, Date date){
        Abscence abscence = new Abscence(date, worker);
        abscence.setWorkerId(worker.getId());
        em.persist(abscence);
    }
    
    public void editAbscence(Long absId, Worker worker, Date date){
        Abscence find = em.find(Abscence.class, absId);
        find.setWorker(worker);
        find.setDate(date);
        em.merge(find);
    }
}
