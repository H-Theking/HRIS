/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.WorkExperience;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class WorkExperienceFacade extends AbstractFacade<WorkExperience> {

    @EJB
    private EmployeeFacade employeeFacade;

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkExperienceFacade() {
        super(WorkExperience.class);
    }

    public void addWorkExperience(String workerId, String company, String jobTitle,
            Date startdate, Date endDate, String comment) {
        WorkExperience experience = new WorkExperience(company, jobTitle);
        experience.setStartDate(startdate);
        experience.setEndDate(endDate);
        experience.setComment(comment);
        experience.setWorker(employeeFacade.find(workerId));
        em.persist(experience);
    }

    public void editExperience(WorkExperience exp , String company, String jobTitle,
            Date startdate, Date endDate, String comment) {
        exp.setComment(comment);
        exp.setCompany(company);
        exp.setEndDate(endDate);
        exp.setStartDate(startdate);
        exp.setJobtitle(jobTitle);
        em.merge(exp);
    }

    public List<WorkExperience> findExperiences(String workerId){
        return em.createQuery("SELECT e FROM WorkExperience e WHERE "
                + "e.worker.id = '" + workerId + "'").getResultList();
    }
}
