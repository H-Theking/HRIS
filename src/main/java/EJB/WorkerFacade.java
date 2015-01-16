/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.ContactDetails;
import Entities.EmergencyContact;
import Entities.EmployeeHasJob;
import Entities.Worker;
import java.util.ArrayList;
import java.util.Date;
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
public class WorkerFacade extends AbstractFacade<Worker> {

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkerFacade() {
        super(Worker.class);
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
            Worker.CivilStatus civilStatus, String nationality, int numberOfChildren) {
        try {
            Worker find = em.find(Worker.class, id);
            find.setFirstName(firstName);
            find.setMiddlenames(middleNames);
            find.setLastName(lastName);
            find.setPlaceOfBirth(placeOfBirth);
            find.setBirthDate(birthDate);
            find.setGender(gender);
            find.setCivilStatus(civilStatus);
            find.setNationality(nationality);
            find.setNumberOfChildren(numberOfChildren);
            em.persist(find);
//            int executeUpdate = em.createQuery("UPDATE Worker w SET"
//                    + " w.firstName = '" + firstName
//                    + "', w.middlenames = '" + middleNames
//                    + "', w.lastName = '" + lastName
//                    + "', w.birthDate = '" + birthDate
//                    + "', w.placeOfBirth = '" + placeOfBirth
//                    + "', w.nationality = '" + nationality
//                    + "', w.gender = '" + gender
//                    + "', w.civilStatus = '" + civilStatus
//                    + "', w.numberOfChildren = '" + numberOfChildren
//                    + "' WHERE w.id = :id")
//                    .setParameter("id", id).executeUpdate();
        } catch (EJBException e) {
            throw new EJBException(e);
        }
    }
    
    public List<EmployeeHasJob> getData(){
        List<EmployeeHasJob> job = (List<EmployeeHasJob>) em.createNamedQuery("getEmployeeData").getResultList();
        return job;
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

    public List<Worker> findEmployeeByContractStatus(EmployeeHasJob.Contract status) {
        return (List<Worker>) em.createNamedQuery("findEmployeesByContractStatus")
                .setParameter("status", status).getResultList();
    }

    public List<Worker> findWorkerByName(String name) {
        name = name.concat("%");
        List<Worker> workers = new ArrayList<>();
        try {
            workers = em.createQuery("SELECT w FROM Worker w WHERE w.firstName LIKE :name "
                    + "OR w.middlenames LIKE :name OR w.lastName LIKE :name")
                    .setParameter("name", name).getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return workers;
    }

    public void editPersonalDetails(String id, String firstName, String middleNames,
            String lastName, Date birthDate, String birthPlace, String nationality,
            String gender, String civilStatus, int nOChildren) {
        int executeUpdate = em.createQuery("UPDATE Worker w SET"
                + "  w.firstName = " + firstName
                + ", w.middlenames = " + middleNames
                + ", w.lastName = " + lastName
                + ", w.birthDate = " + birthDate
                + ", w.placeOfBirth = " + birthPlace
                + ", w.gender = " + gender
                + ", w.civilStatus = " + civilStatus
                + ", w.nationality = " + nationality
                + ", w.numberOfChildren = " + nOChildren
                + " WHERE id = :id ").setParameter("id", id).executeUpdate();
    }

    public void editContactDetails(String contactId, String country, String region,
            String city, String streetAddress, String mobileNumber,
            String homeNumber, String workNumber, String email) {

        int executeUpdate = em.createQuery("UPDATE ContactDetails c "
                + "SET C.country = " + country
                + ", c.region = " + region
                + ", c.city = " + city
                + ", c.homeNumber = " + homeNumber
                + ", c.workNumber = " + workNumber
                + ", c.mobileNumber = " + mobileNumber
                + ", c.email = " + email
                + ", WHERE c.contactId = :id")
                .setParameter("id", contactId).executeUpdate();
    }

    public void addemergencyContact(String employeeId, String name, String relationship, String mobileNumber,
            String homeNumber, String workNumber) {
        EmergencyContact ec = new EmergencyContact();
        Worker worker = em.find(Worker.class, employeeId);
        ec.setWorker(worker);
        em.persist(ec);
    }

    public void editEmergencyContact(Long contactId, String relationship, String name,
            String mobileNumber, String homeNumber, String workNumber) {

        em.createQuery("UPDATE EmergencyContact e SET"
                + "  E.name = " + name
                + ", e.relationship = " + relationship
                + ", e.homeNumber = " + homeNumber
                + ", e.workNumber = " + workNumber
                + ", e.mobileNumber = " + mobileNumber)
                .setParameter("contactId", contactId).executeUpdate();
    }

    public void deleteEmergencyContact(Long id) {
        EmergencyContact find = em.find(EmergencyContact.class, id);
        em.remove(find);
    }

    public List<EmergencyContact> getEmergencyContact(String id) {
        Worker worker = em.find(Worker.class, id);
        List<EmergencyContact> ec = em.createQuery("SELECT w FROM EmergencyContact w"
                + " WHERE w.worker.id = :id").setParameter("id", id).getResultList();
        return ec;
    }
}
