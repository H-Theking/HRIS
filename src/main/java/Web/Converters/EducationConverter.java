/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.Converters;

import EJB.EducationFacade;
import Entities.Education;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Harvey
 */
@FacesConverter(forClass = Entities.Education.class,value = "education")
public class EducationConverter implements Converter {
    @EJB
    EducationFacade educationFacade;

    private Education education;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        education = educationFacade.find(string);
        return education;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Education) o).getLevel();
    }
}
