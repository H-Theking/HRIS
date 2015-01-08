/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.ContactDetails;
import Entities.EmployeeHasJob;
import Entities.EmployeeHasJob.Contract;
import Entities.Job;
import Entities.Worker;
//import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
public class WorkerAndJobFacade extends AbstractFacade<Job> {

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkerAndJobFacade() {
        super(Job.class);
    }

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     WORKER CLASS BUSSINESS METHODS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    public void createWorker(String id, String firstName, String lastName, Date birthDate) {
        try {
            Worker worker = new Worker(id, firstName, lastName, birthDate);
//            logger.log(Level.INFO, "Created part {0}-{1}", new Object[]{worker.getId(), firstName});
            EmployeeHasJob job = new EmployeeHasJob(id);
            ContactDetails contactDetails = new ContactDetails(id);
            em.persist(worker);
            em.persist(contactDetails);
            em.persist(job);
//            logger.log(Level.INFO, "Persisted part {0}-{1}", new Object[]{worker.getId(), firstName});
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public void createWorker(String id, String firstName, String middleNames, String lastName) {
        try {
            Worker worker = new Worker(id, firstName, middleNames, lastName);
            EmployeeHasJob job = new EmployeeHasJob(id);
            ContactDetails contactDetails = new ContactDetails(id);
            em.persist(worker);
            em.persist(contactDetails);
            em.persist(job);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public void editWorkerDetails(String id, String firstName, String middleNames,
            String lastName, Date birthDate, String placeOfBirth, Worker.Gender gender,
            Worker.CivilStatus civilStatus, String nationality, String numberOfChildren) {
        try {
            int executeUpdate = em.createQuery("UPDATE Worker w SET w.firstName = " + firstName
                    + ", w.middlenames = " + middleNames
                    + ", w.lastName = " + lastName
                    + ", w.birthDate = " + birthDate
                    + ", w.placeOfBirth = " + placeOfBirth
                    + ", w.nationality = " + nationality
                    + ", w.gender =" + gender
                    + ", w.civilStatus = " + civilStatus
                    + ", w.numberOfChildren = " + numberOfChildren
                    + " WHERE w.id = :id")
                    .setParameter("id", id).executeUpdate();
        } catch (EJBException e) {
            throw new EJBException(e);
        }
    }

    public List<Worker> getWorkers() {
        List<Worker> workers = (List<Worker>) em.createNamedQuery("findEmployees").getResultList();
        return workers;
    }

    public void removeWorker(String workerId) {
        try {
            Worker worker = em.find(Worker.class, workerId);
            em.remove(worker);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }

    }

    public Worker findWorkerById(String id) throws EJBException {
        return (Worker) em.createNamedQuery("findEmployeesById")
                .setParameter("id", id).getSingleResult();
    }

    public List<Worker> findWorkerByEmploymentStatus(String eStatus) throws EJBException {
        return (List<Worker>) em.createNamedQuery("findEmployeesByEmploymentStatus")
                .setParameter("eStatus", eStatus).getResultList();
    }

    public List<Worker> findEmployeeByJobTitle(String title) throws EJBException {
        return (List<Worker>) em.createNamedQuery("findEmployeesByJobTitle")
                .setParameter("jobTitle", title).getResultList();
    }

    public List<Worker> findEmployeeBySubUnit(String subUnit) throws EJBException {
        return (List<Worker>) em.createNamedQuery("findEmployeesBySubUnit")
                .setParameter("jobTitle", subUnit).getResultList();
    }

    public List<Worker> findEmployeeByContractStatus(Contract status) {
        return (List<Worker>) em.createNamedQuery("findEmployeesByContractStatus")
                .setParameter("status", status).getResultList();
    }

    public List<String> findWorkerByName(String name) {
        List<String> names = new ArrayList<>();
        try {
            List workers = em.createQuery("SELECT w FROM Worker w WHERE w.firstName = :name")
                    .setParameter("name", name).getResultList();
            for (Iterator iterator = workers.iterator(); iterator.hasNext();) {
                Worker worker = (Worker) iterator.next();
                names.add(worker.getFirstName().concat(" ").concat(worker.getLastName()));
            }
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return names;
    }
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     JOB CLASS BUSSINESS METHODS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/

    public void createJob(String jobTitle, String jobDescription) {
        try {
            Job job = new Job();
//            logger.log(Level.INFO, "Created part {0}-{1}", new Object[]{job.getId(), jobTitle});
            em.persist(job);
//            logger.log(Level.INFO, "Persisted part {0}-{1}", new Object[]{job.getId(), jobTitle});
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public void renameJob(String jobTitle) {
        try {
            int executeUpdate = em.createNamedQuery("UPDATE j FROM Job j SET j.jobTitle = "
                    .concat(jobTitle).concat(" WHERE j.jobTitle = :name"))
                    .setParameter("name", jobTitle).executeUpdate();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeJob(String jobId) {
        try {
            Job job = em.find(Job.class, jobId);
            em.remove(job);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Job> findAllJobs() {
        List<Job> jobs = super.findAll();
        return jobs;
    }

}
