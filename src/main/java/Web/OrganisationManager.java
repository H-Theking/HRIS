/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.OrganisationFacade;
import Entities.Organisation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Harvey
 */
@Named(value = "organisationManager")
@SessionScoped
public class OrganisationManager implements Serializable {

    @EJB
    private OrganisationFacade organisationFacade;

    /**
     * Creates a new instance of OrganisationManager
     */
    private String id;

    private String registrationNumber;

    private String email;

    private String fax;

    private String telephone;

    private String street;

    private String city;

    private String region;

    private String country;

    private int numberOfEmployees;

    private String name;

    public OrganisationManager() {
        Organisation find = organisationFacade.find(id);
        name = find.getName();
        registrationNumber = find.getRegistrationNumber();
        numberOfEmployees = find.getNumberOfEmployees();//to calculate
        country = find.getCountry();
        region = find.getRegion();
        city = find.getCity();
        street = find.getStreet();
        telephone = find.getTelepone();
        fax = find.getFax();
        email = find.getEmail();
    }

    public void editInformation() {
        Organisation find = organisationFacade.find(id);
        find.setName(name);
        find.setRegistrationNumber(registrationNumber);
        find.setCountry(country);
        find.setRegion(region);
        find.setCity(city);
        find.setStreet(street);
        find.setTelepone(telephone);
        find.setFax(fax);
        find.setEmail(email);
        organisationFacade.edit(find);
    }
    
    public void storeId(){
        id = name;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
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
     * Get the value of numberOfEmployees
     *
     * @return the value of numberOfEmployees
     */
    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    /**
     * Set the value of numberOfEmployees
     *
     * @param numberOfEmployees new value of numberOfEmployees
     */
    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
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
     * Get the value of telephone
     *
     * @return the value of telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Set the value of telephone
     *
     * @param telephone new value of telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Get the value of fax
     *
     * @return the value of fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * Set the value of fax
     *
     * @param fax new value of fax
     */
    public void setFax(String fax) {
        this.fax = fax;
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
     * Get the value of registrationNumber
     *
     * @return the value of registrationNumber
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Set the value of registrationNumber
     *
     * @param registrationNumber new value of registrationNumber
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

}
