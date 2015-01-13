/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Job;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class JobFacade extends AbstractFacade<Job> {

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobFacade() {
        super(Job.class);
    }

    public List<Job> getJobs() {
        List<Job> resultList = (List<Job>) em.createNamedQuery("findAllJobs").getResultList();
        return resultList;
    }

    public void addJob(String jobtitle, String jobDescription, String note)
            throws EJBException {
        Job job = new Job(jobtitle, jobDescription, note);
        em.persist(job);
    }

    public void editJob(String oldName, String jobTitle, String description, String note) 
            throws EJBException {
        em.createQuery("UPDATE Job j SET j.jobTitle = " + jobTitle 
                + "j.jobDescription = " + description
                + "j.note" + note 
                + "WHERE j.jobtitle = " + oldName).executeUpdate();
    }
    
    public void deleteJob(String jobTitle){
        Job find = em.find(Job.class, jobTitle);
        em.remove(find);
    }
    
    public Job findJob(String jobTitle){
      return  em.find(Job.class, jobTitle);
    }
}
