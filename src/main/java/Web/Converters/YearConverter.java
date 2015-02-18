/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.Converters;

import java.time.Year;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Harvey
 */
@FacesConverter(forClass = Entities.EmployeeHasEducation.class, value = "yearConverter")
public class YearConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int year = Integer.valueOf(string);
        if(Year.now().isBefore(Year.of(year))){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Entrez une date anterieure a " + string, null);
            throw new ConverterException(message);
        }
        return Year.of(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return o.toString();
    }
    
}
