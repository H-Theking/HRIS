/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.EmployeeImage;
import Entities.Worker;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class EmployeeImagesFacade extends AbstractFacade<EmployeeImage> {

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeImagesFacade() {
        super(EmployeeImage.class);
    }

    public void setImage(String employeeId, byte[] bytes, String imageName, String imageType) {
        EmployeeImage find = em.find(EmployeeImage.class, employeeId);
        find.setImage(bytes);
        find.setImageName(imageName);
        find.setImageType(imageType);
        em.merge(find);
    }
}