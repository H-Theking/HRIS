/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.LocationFacade;
import Entities.Location;
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
@Named(value = "locationManager")
@SessionScoped
public class LocationManager implements Serializable {
    @EJB
    private LocationFacade locationFacade;

    private Long id;

    private String name;
    
    private String country;

    private String region;

    private String city;
    
    private String street;

    private String telephone;

    private String fax;

    private String email;

    private Location[] selectedLocations;
    
    private Location found;

    /**
     * Creates a new instance of LocationManager
     */
    public LocationManager() {
    }
    
    public void clearFields(){
        name = null;
        country = null;
        region = null;
        city = null;
        street = null;
        telephone = null;
        fax = null;
        email = null;
    }
    
    public void addLocation(){
        if (id == null) {
            locationFacade.addLocation(name, country, region, city, street, 
                telephone, fax, email);
        }else{
            found.setName(name);
            found.setCountry(country);
            found.setCity(city);
            found.setStreet(street);
            found.setTelepone(telephone);
            found.setFax(fax);
            found.setEmail(email);
            locationFacade.edit(found);
        }
        clearFields();
    }
    
    public void deleteLocations(){
        for(Location location: selectedLocations){
            locationFacade.remove(location);
        }
    }
    
    public List<Location> getLocations(){
        return locationFacade.findAll();
    }
    
    public void findLocation(AjaxBehaviorEvent event){
        UIParameter param = (UIParameter) event.getComponent().findComponent("location");
        Long loc = (Long) param.getValue();
        found = locationFacade.find(loc);
        name = found.getName();
        country = found.getCountry();
        region = found.getRegion();
        city = found.getCity();
        street = found.getStreet();
        telephone = found.getTelepone();
        fax = found.getFax();
        email= found.getEmail();
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
     * Get a collection of all locations
     *
     * @return the value of selectedLocations
     */
    public Location[] getSelectedLocations() {
        return selectedLocations;
    }

    /**
     * Set the value of selectedLocations
     *
     * @param selectedLocations new value of selectedLocations
     */
    public void setSelectedLocations(Location[] selectedLocations) {
        this.selectedLocations = selectedLocations;
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
}
