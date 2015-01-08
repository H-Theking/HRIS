/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Education;
import Entities.EmployeeHasEducation;
import Entities.Worker;
import java.time.Year;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class EducationFacade extends AbstractFacade<Education> {

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EducationFacade() {
        super(Education.class);
    }

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     Eduction Class Business Methods
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    public void addLevel(String level) {
        Education degree = new Education(level);
        em.persist(degree);
    }

    public void removeLevel(String level) {
        Education degree = (Education) em.find(Education.class, level);
        em.remove(degree);
    }

    public void editLevel(String oLevel, String nLevel) {
        em.createQuery("UPDATE Education e"
                + " SET e.level = " + nLevel
                + " WHERE e.level = :degree")
                .setParameter("degree", oLevel).executeUpdate();
    }

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     EMPLOYEEHASEDUCATION BUSINESS METHODS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    public void addworkerDegree(String employeeId) {///Standby
        EmployeeHasEducation edu = new EmployeeHasEducation();
//        find the worker with this ID
        Worker worker = em.find(Worker.class, employeeId);
//        Add the degree to his profile
        System.out.println(worker);
        edu.setWorker(worker);
        em.persist(edu);
    }

    public void editWorkerDegree(String id, String institute, String degree,
            Year year, float score, Date startDate, Date endDate) {
        em.createQuery("UPDATE EmployeeHasEducation edu SET"
                + "  EDU.institute = " + institute
                + ", edu.degree.level = " + degree
                + ", edu.year = " + year
                + ", edu.score = " + score
                + ", edu.startDate = " + startDate
                + ", edu.endDate = " + endDate
                + " WHERE edu.id = :id").setParameter("id", id).executeUpdate();
    }

    public void deleteWorkerDegree(String id) {
        EmployeeHasEducation edu = em.find(EmployeeHasEducation.class, id);
        em.remove(edu);
    }
}
