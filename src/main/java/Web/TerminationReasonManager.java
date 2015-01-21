/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.TerminationReasonFacade;
import Entities.TerminationReason;
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
@Named(value = "terminationReasonManager")
@SessionScoped
public class TerminationReasonManager implements Serializable {
    @EJB
    private TerminationReasonFacade terminationReasonFacade;

    private Long id;

    private String reason;

    private String previousReason;

    private TerminationReason[] selectedReasons;
    private List<TerminationReason> reasons;

    /**
     * Get the value of selectedReasons
     *
     * @return the value of selectedReasons
     */
    public TerminationReason[] getSelectedReasons() {
        return selectedReasons;
    }

    /**
     * Set the value of selectedReasons
     *
     * @param selectedReasons new value of selectedReasons
     */
    public void setSelectedReasons(TerminationReason[] selectedReasons) {
        this.selectedReasons = selectedReasons;
    }

    /**
     * Get the value of previousReason
     *
     * @return the value of previousReason
     */
    public String getPreviousReason() {
        return previousReason;
    }

    /**
     * Set the value of previousReason
     *
     * @param previousReason new value of previousReason
     */
    public void setPreviousReason(String previousReason) {
        this.previousReason = previousReason;
    }

    /**
     * Get the value of reason
     *
     * @return the value of reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Set the value of reason
     *
     * @param reason new value of reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Creates a new instance of TerminationReasonManager
     */
    public TerminationReasonManager() {
    }
    
    public void clearFields(AjaxBehaviorEvent event){
        id = null;
        reason = previousReason = null;
    }
    
    public void addEditReason(){
        if (previousReason == null) {
             terminationReasonFacade.addReason(reason);
        }else{
            terminationReasonFacade.editReason(id, reason);
        }
        
    }
    
    public List<TerminationReason> getReasons(){
        reasons = terminationReasonFacade.findAll();
        return reasons;
    }
    
    public void findReason(AjaxBehaviorEvent event){
        UIParameter parameter= (UIParameter) event.getComponent().findComponent("reason");
        Long id = (Long) parameter.getValue();
        TerminationReason find = terminationReasonFacade.find(id);
        previousReason = reason = find.getReason();
    }
    
    public void deleteReasons(){
        for (TerminationReason reason: selectedReasons) {
            terminationReasonFacade.remove(reason);
        }
    }
}
