/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Department;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class DepartmentFacade extends AbstractFacade<Department> {

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartmentFacade() {
        super(Department.class);
    }

    public void createDepartment(String departmentname, String subUnit) throws EJBException {
        Department department = new Department(subUnit, subUnit);//create new department
        em.persist(department);//store in database
    }

    public void renameDepartment(String newName, String oldname) throws EJBException {
        int executeUpdate = em.createQuery("UPDATE d FROM Department d SET d.name = "
                .concat(newName).concat("WHERE d.name = :dname"))
                .setParameter("dname", oldname).executeUpdate();
    }
    
    public void removeDepartment(String department){
        int executeUpdate = em.createQuery("DELETE FROM Department d WHERE d.name = :name")
                .setParameter("name", department).executeUpdate();
    }
}
