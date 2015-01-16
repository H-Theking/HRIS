/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.EmploymentSatusFacade;
import Entities.EmploymentStatus;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "employmentStatusManager")
@SessionScoped
public class EmploymentStatusManager implements Serializable {

    @EJB
    private EmploymentSatusFacade employmentSatusFacade;

    private String name;
    
    private EmploymentStatus[] selectedStatus;

    private String old;

    /**
     * Creates a new instance of EmploymentStatusManger
     */
    public EmploymentStatusManager() {
    }
    
    public void clearFields(){
        name = old = null;
    }
    
    public void addEditStatus(){
        if (old == null) {
            employmentSatusFacade.addStatus(name);
        }else{
            employmentSatusFacade.editStatus(old, name);
        }
        clearFields();
    }
    
    public void deleteStatus(){
        for(EmploymentStatus status: selectedStatus){
            employmentSatusFacade.remove(status);
        }
    }
    
    public List<EmploymentStatus> getStatusList(){
        return employmentSatusFacade.findAll();
    }
    
    public void findStatus(AjaxBehaviorEvent event){
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("status");
        String status = parameter.getValue().toString();//could get this name directly
        //that is name = old = status;
        //But lets do it the right way - get the property from its object
        EmploymentStatus find = employmentSatusFacade.find(status);
        name = old = find.getStatus();
    }
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of old
     *
     * @return the value of old
     */
    public String getOld() {
        return old;
    }

    /**
     * Set the value of old
     *
     * @param old new value of old
     */
    public void setOld(String old) {
        this.old = old;
    }

    /**
     * Get the value of selectedStatus
     *
     * @return the value of selectedStatus
     */
    public EmploymentStatus[] getSelectedStatus() {
        return selectedStatus;
    }

    /**
     * Set the value of selectedStatus
     *
     * @param selectedStatus new value of selectedStatus
     */
    public void setSelectedStatus(EmploymentStatus[] selectedStatus) {
        this.selectedStatus = selectedStatus;
    }


}
