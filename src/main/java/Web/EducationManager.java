/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.EducationFacade;
import Entities.Education;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIParameter;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "educationManager")
@SessionScoped
public class EducationManager implements Serializable {

    @EJB
    private EducationFacade educationFacade;
    private String degree;
    private String oldName;
    private Education[] selectedLevels;
    private List<Education> levels;

    /**
     * Creates a new instance of EducationManager
     */
    public EducationManager() {
    }

    public void clearFields() {
        oldName = degree = null;
    }

    public void addLevel(){
        if (oldName == null) {
            educationFacade.addLevel(degree);
        }else{
            educationFacade.editLevel(oldName, degree);
        }
    }
    
    public void deleteLevels(){
        for (Education selectedLevel : selectedLevels) {
            educationFacade.remove(selectedLevel);
        }
    }
    
    public List<Education> getLevels(){
        return levels = educationFacade.findAll();
    }
    
    public void findLevel(AjaxBehaviorEvent event) throws EJBException{
        UIParameter param = (UIParameter) event.getComponent().findComponent("level");
        String currentDegree = param.getValue().toString();
        Education find = educationFacade.find(currentDegree);
        oldName = degree = find.getLevel();
        System.out.println(oldName);
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public Education[] getSelectedLevels() {
        return selectedLevels;
    }

    public void setSelectedLevels(Education[] selectedLevels) {
        this.selectedLevels = selectedLevels;
    }
}
