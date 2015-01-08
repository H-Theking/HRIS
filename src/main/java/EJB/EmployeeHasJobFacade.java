/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.EmployeeHasJob;
import Entities.PayGrade;
import Entities.TerminatedJob;
//import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.util.Date;
//import java.util.logging.Level;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class EmployeeHasJobFacade extends AbstractFacade<EmployeeHasJob> {

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeHasJobFacade() {
        super(EmployeeHasJob.class);
    }

    public void editJobDetails(String id, String jobTitle, String employmentStatus,
            Date joinedDate, Date startDate, Date endDate, String subUnit,
            String location) {
        try {
            em.createQuery("UPDATE EmployeeHasJob ej SET"
                    + "  ej.jobTitle = " + jobTitle
                    + ", ej.employmentStatus = " + employmentStatus
                    + ", ej.joinedDate = " + joinedDate
                    + ", ej.startDate = " + startDate
                    + ", ej.endDate = " + endDate
                    + ", ej.subUnit =" + subUnit
                    + ", ej.location = " + location
                    + " WHERE id = :id").setParameter("id", id).executeUpdate();
        } catch (EJBException e) {
            throw new EJBException(e);
        }
    }

    public void terminateJob(String workerId, String reason, Date date, String note) {

        try {
            TerminatedJob terminatedJob = new TerminatedJob(reason, date, note);
            em.persist(terminatedJob);
            EmployeeHasJob job = em.find(EmployeeHasJob.class, workerId);
            job.setStatus(EmployeeHasJob.Status.Disabled);
        } catch (EJBException e) {
            throw new EJBException(e);
        }
    }

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     PAY GRADE BUSINNESS METHODS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    public void addPayGrade(String name, float minimum, float maximum, String currency) {
        try {
            PayGrade payGrade = new PayGrade(name, minimum, maximum, currency);
            em.persist(payGrade);
        } catch (EJBException exception) {
            throw new EJBException(exception);
        }
    }

    public void editPayGrade(String name, float minimum, float maximum, String currency) {
        try {
            PayGrade payGrade = new PayGrade(name, minimum, maximum, currency);
            em.persist(payGrade);
//            logger.log(Level.INFO, "Persisted paygrade  {0}.", name);
        } catch (EJBException exception) {
            throw new EJBException(exception);
        }
    }

    public void deletePayGrade(String name) {
        try {
            PayGrade payGrade = em.find(PayGrade.class, name);
            em.remove(payGrade);
//            logger.log(Level.INFO, "Removed paygrade  {0}.", name);
        } catch (EJBException exception) {
            throw new EJBException(exception);
        }
    }
}
