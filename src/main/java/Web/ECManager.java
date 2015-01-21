/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.EmergencyContactFacade;
import Entities.EmergencyContact;
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
@Named(value = "eCManager")
@SessionScoped
public class ECManager implements Serializable {

    @EJB
    private EmergencyContactFacade ecFacade;

    /**
     * Creates a new instance of ECManager
     */
    private String contactName;

    private String relationship;

    private String homePhone;

    private String mobilePhone;

    private String workPhone;

    private String workerId;

    private String oldContactName;

    private Long ecId;

    private EmergencyContact[] selectedContacts;

    public ECManager() {
    }

    public void clearFields() {
        contactName = relationship = homePhone = workPhone = mobilePhone = null;
        ecId = null;
    }

    public void addEC() {
        if (oldContactName == null) {
            ecFacade.addEmergencyContact(workerId, contactName,
                    relationship, mobilePhone, homePhone, workPhone);
        } else {
            ecFacade.editEmergencyContact(ecId, contactName, relationship, mobilePhone, homePhone, workPhone);
        }
        clearFields();
    }

    public void deleteEmergencyContact() {
        EmergencyContact find = ecFacade.find(ecId);
        ecFacade.remove(find);
    }

    public List<EmergencyContact> getECs() {
        return ecFacade.getEmergencyContacts(workerId);
    }

    public void findEC(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("ec");
        Long id = (Long) parameter.getValue();
        EmergencyContact ec = ecFacade.find(id);
        oldContactName = contactName = ec.getName();
        relationship = ec.getRelationship();
        homePhone = ec.getHomeNumber();
        workPhone = ec.getWorkNumber();
        mobilePhone = ec.getMobileNumber();
    }
    
    public void findEmployeeContact(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("worker");
        workerId = parameter.getValue().toString();
    }

    /**
     * Get the value of oldContactName
     *
     * @return the value of oldContactName
     */
    public String getOldContactName() {
        return oldContactName;
    }

    /**
     * Set the value of oldContactName
     *
     * @param oldContactName new value of oldContactName
     */
    public void setOldContactName(String oldContactName) {
        this.oldContactName = oldContactName;
    }

    /**
     * Get the value of ecId
     *
     * @return the value of ecId
     */
    public Long getEcId() {
        return ecId;
    }

    /**
     * Set the value of ecId
     *
     * @param ecId new value of ecId
     */
    public void setEcId(Long ecId) {
        this.ecId = ecId;
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

    /**
     * Get the value of workPhone
     *
     * @return the value of workPhone
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * Set the value of workPhone
     *
     * @param workPhone new value of workPhone
     */
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    /**
     * Get the value of mobilePhone
     *
     * @return the value of mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Set the value of mobilePhone
     *
     * @param mobilePhone new value of mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * Get the value of homePhone
     *
     * @return the value of homePhone
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * Set the value of homePhone
     *
     * @param homePhone new value of homePhone
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * Get the value of relationship
     *
     * @return the value of relationship
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * Set the value of relationship
     *
     * @param relationship new value of relationship
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * Get the value of contactName
     *
     * @return the value of contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Set the value of contactName
     *
     * @param contactName new value of contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Get the value of selectedContacts
     *
     * @return the value of selectedContacts
     */
    public EmergencyContact[] getSelectedContacts() {
        return selectedContacts;
    }

    /**
     * Set the value of selectedContacts
     *
     * @param selectedContacts new value of selectedContacts
     */
    public void setSelectedContacts(EmergencyContact[] selectedContacts) {
        this.selectedContacts = selectedContacts;
    }

}
