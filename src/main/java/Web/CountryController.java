/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.CountriesFacade;
import Entities.Countries;
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
@Named(value = "countryController")
@SessionScoped
public class CountryController implements Serializable {
    @EJB
    private CountriesFacade countriesFacade;
    
    private String country;

    private String nationality;

    private Long id;

    private Countries[] selectedCountries;

    /**
     * Get the value of selectedCountries
     *
     * @return the value of selectedCountries
     */
    public Countries[] getSelectedCountries() {
        return selectedCountries;
    }

    /**
     * Set the value of selectedCountries
     *
     * @param selectedCountries new value of selectedCountries
     */
    public void setSelectedCountries(Countries[] selectedCountries) {
        this.selectedCountries = selectedCountries;
    }
    
        /**
     * Get the value of nationality
     *
     * @return the value of nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Set the value of nationality
     *
     * @param nationality new value of nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
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
     * Creates a new instance of CoutryController
     */
    public CountryController() {
    }
    
    public void clearFields(){
        id = null;
        country = null;
    }
    
    public void addCountry(){
        countriesFacade.addCountry(country, nationality);
    }
    
    public void editCountry(){
        countriesFacade.editcountry(id, country, nationality);
    }
    
    public List<Countries> getCoutries(){
        return countriesFacade.findAll();
    }
    
    public void findCountry(AjaxBehaviorEvent event){
        UIParameter parameter = (UIParameter)event.getComponent().findComponent("country");
        id = (Long) parameter.getValue();
        Countries find = countriesFacade.find(id);
        country = find.getCountry();
        nationality = find.getNationality();
    }
    
    public void removeCountries(){
        for(Countries selected: selectedCountries){
            countriesFacade.remove(selected);
        }
    }
}
