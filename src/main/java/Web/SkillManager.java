/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.SkillFacade;
import Entities.Skill;
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
@Named(value = "skillManager")
@SessionScoped
public class SkillManager implements Serializable {
    @EJB
    private SkillFacade skillFacade;
    
    private String name;
    
    private String description;

    private Skill[] selectedSkills;

    private String oldName;

    /**
     * Creates a new instance of SkillManager
     */
    public SkillManager() {
    }
    
     public void clearFields() {
        oldName = name = description = null;
    }

    public void addSkill(){
        if (oldName == null) {
            skillFacade.addSkill(name, description);
        }else{
            skillFacade.editskill(oldName, name, description);
        }
    }
    
    public void deleteSkills(){
        for (Skill skill : selectedSkills) {
            skillFacade.remove(skill);
        }
    }
    
    public List<Skill> getAllSkills(){
        return skillFacade.findAll();
    }
    
    public void findskill(AjaxBehaviorEvent event) throws EJBException{
        UIParameter param = (UIParameter) event.getComponent().findComponent("skill");
        String thisSkill = param.getValue().toString();
        Skill find = skillFacade.find(thisSkill);
        oldName = name = find.getName();
        description = find.getDescription();
    }

    /**
     * Get the value of oldName
     *
     * @return the value of oldName
     */
    public String getOldName() {
        return oldName;
    }

    /**
     * Set the value of oldName
     *
     * @param oldName new value of oldName
     */
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    /**
     * Get the value of selectedSkills
     *
     * @return the value of selectedSkills
     */
    public Skill[] getSelectedSkills() {
        return selectedSkills;
    }

    /**
     * Set the value of selectedSkills
     *
     * @param selectedSkills new value of selectedSkills
     */
    public void setSelectedSkills(Skill[] selectedSkills) {
        this.selectedSkills = selectedSkills;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
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
}