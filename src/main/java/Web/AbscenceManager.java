/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.AbscenceFacade;
import EJB.EmployeeFacade;
import Entities.Abscence;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "abscenceManager")
@RequestScoped
public class AbscenceManager {
    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private AbscenceFacade abscenceFacade;

    
    /**
     * Creates a new instance of AbscenceManager
     */
    public AbscenceManager() {
        controller = new InterfaceController();
        manager = new UserManager();
    }
    
    private String workerId;
    
    private String name;

    private Date date;

    private Long absId;

    private Abscence[] selectedAbs;
    
    private InterfaceController controller;
    
    private UserManager manager;
    
    public void clearFields(){
        workerId = null;
        date = null;
        absId = null;
    }
    
    public void addAbscence(){
        abscenceFacade.addAbscence(employeeFacade.find(workerId), date);
    }
    
    public void editAbscence(){
        abscenceFacade.editAbscence(absId, employeeFacade.find(workerId), date);
    } 
    
    public void removeAbs(){
        for(Abscence abscence:selectedAbs){
            abscenceFacade.remove(abscence);
        }
    }
    
    public List<Abscence> getAbscences(){
        return abscenceFacade.findAll();
    } 
    
     public void findAbscence(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("abs");
        Abscence abscence = abscenceFacade.find((Long) parameter.getValue()); 
        date = abscence.getDate();
        name = abscence.getWorker().getFirstName().concat(" ").
                concat(abscence.getWorker().getLastName());
        workerId = abscence.getWorkerId();
     }
     
     

    /**
     * Get the value of selectedAbs
     *
     * @return the value of selectedAbs
     */
    public Abscence[] getSelectedAbs() {
        return selectedAbs;
    }

    /**
     * Set the value of selectedAbs
     *
     * @param selectedAbs new value of selectedAbs
     */
    public void setSelectedAbs(Abscence[] selectedAbs) {
        this.selectedAbs = selectedAbs;
    }

    /**
     * Get the value of absId
     *
     * @return the value of absId
     */
    public Long getAbsId() {
        return absId;
    }

    /**
     * Set the value of absId
     *
     * @param absId new value of absId
     */
    public void setAbsId(Long absId) {
        this.absId = absId;
    }

    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the value of date
     *
     * @param date new value of date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the value of workerId
     *
     * @return the value of workerId
     */
    public String getWorkerId() {
        return workerId;
    }

    /**
     * Set the value of workerId
     *
     * @param workerId new value of workerId
     */
    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InterfaceController getController() {
        return controller;
    }

    public void setController(InterfaceController controller) {
        this.controller = controller;
    }

    public UserManager getManager() {
        return manager;
    }

    public void setManager(UserManager manager) {
        this.manager = manager;
    }
}