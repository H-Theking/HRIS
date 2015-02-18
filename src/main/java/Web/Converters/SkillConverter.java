/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.Converters;

import EJB.EmployeeSkillFacade;
import Entities.EmployeeSkill;
import Entities.EmployeeSkillPK;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Harvey
 */
@FacesConverter(forClass = Entities.EmployeeSkill.class,value = "skillConverter")
public class SkillConverter implements Converter {

    @EJB
    EmployeeSkillFacade skillFacade;
    private String workerId;
    private String skill;    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return skillFacade.find(new EmployeeSkillPK(skill, workerId));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return  o.toString();
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

}
