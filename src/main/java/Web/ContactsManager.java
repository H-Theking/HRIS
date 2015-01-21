/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.ContactDetailsFacade;
import Entities.ContactDetails;
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
@Named(value = "contactsManager")
@SessionScoped
public class ContactsManager implements Serializable {

    @EJB
    private ContactDetailsFacade contactDetailsFacade;

    private String country;

    private String region;

    private String city;

    private String street;

    String employeeId;

    private String email;
    private String mobilePhone;
    private String workPhone;
    private String homePhone;

    public void editContact() {
        contactDetailsFacade.editContactDetails(employeeId, country, region,
                city, street, mobilePhone, homePhone, workPhone, email);
    }

    public void findEmployeeContact(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("worker");
        employeeId = parameter.getValue().toString();
        ContactDetails cd = contactDetailsFacade.find(employeeId);
        country = cd.getCountry();
        region = cd.getRegion();
        city = cd.getCity();
        street = cd.getStreetAddress();
        mobilePhone = cd.getMobileNumber();
        homePhone = cd.getHomeNumber();
        workPhone = cd.getWorkNumber();
        email = cd.getEmail();
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of employeeId
     *
     * @return the value of employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Set the value of employeeId
     *
     * @param employeeId new value of employeeId
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Get the value of street
     *
     * @return the value of street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set the value of street
     *
     * @param street new value of street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the value of region
     *
     * @return the value of region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Set the value of region
     *
     * @param region new value of region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public ContactDetailsFacade getContactDetailsFacade() {
        return contactDetailsFacade;
    }

    public void setContactDetailsFacade(ContactDetailsFacade contactDetailsFacade) {
        this.contactDetailsFacade = contactDetailsFacade;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    
    /**
     * Creates a new instance of ContactsManager
     */
    public ContactsManager() {
    }

}
