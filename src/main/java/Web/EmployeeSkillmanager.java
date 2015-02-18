/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.EmployeeSkillFacade;
import Entities.EmployeeSkill;
import Entities.EmployeeSkillPK;
import Web.Converters.SkillConverter;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "employeeSkillmanager")
@SessionScoped
public class EmployeeSkillmanager implements Serializable {

    @EJB
    private EmployeeSkillFacade employeeSkillFacade;

    private String workerId;

    private String skill;

    private int expYears;

    private String comment;

    private EmployeeSkill[] selectedSkills;

    private InterfaceController controller;

    private final SkillConverter converter;
    
    private List<EmployeeSkill> employeeSkills;

    /**
     * Creates a new instance of EmployeeSkillmanager
     */
    public EmployeeSkillmanager() {
        controller = new InterfaceController();
        converter = new SkillConverter();
    }

    public void clearFields() {
        workerId = null;
        skill = null;
        expYears = 0;
        comment = null;
    }

    public void addSkill() {
        converter.setSkill(skill);
        converter.setWorkerId(workerId);
        employeeSkillFacade.addSkill(skill, workerId, expYears, comment);
    }

    public void editSkill() {
        converter.setSkill(skill);
        converter.setWorkerId(workerId);
        employeeSkillFacade.editSkill(new EmployeeSkillPK(skill, workerId),
                expYears, comment);
    }

    public void removeSkill() {
        for (EmployeeSkill employeeSkill : selectedSkills) {
            employeeSkillFacade.remove(employeeSkill);
        }
    }

    public void findSkill(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("skill");
        EmployeeSkill employeeSkill = (EmployeeSkill) parameter.getValue();
        workerId = employeeSkill.getWorkerId();
        skill = employeeSkill.getName();
        expYears = employeeSkill.getExpYears();
        comment = employeeSkill.getComment();
    }

    public List<EmployeeSkill> getSkills() {
        List<EmployeeSkill> findAll = employeeSkillFacade.findAll();
        for (EmployeeSkill employeeSkill : findAll) {
            if (employeeSkill.getWorkerId().equals(workerId)) {
                employeeSkills.add(employeeSkill);
            }
        }
        return employeeSkills;
    }

    public void getEmployee(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("worker");
        workerId = parameter.getValue().toString();
    }

    /**
     * Get the value of selectedSkills
     *
     * @return the value of selectedSkills
     */
    public EmployeeSkill[] getSelectedSkills() {
        return selectedSkills;
    }

    /**
     * Set the value of selectedSkills
     *
     * @param selectedSkills new value of selectedSkills
     */
    public void setSelectedSkills(EmployeeSkill[] selectedSkills) {
        this.selectedSkills = selectedSkills;
    }

    /**
     * Get the value of comment
     *
     * @return the value of comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the value of comment
     *
     * @param comment new value of comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Get the value of expYears
     *
     * @return the value of expYears
     */
    public int getExpYears() {
        return expYears;
    }

    /**
     * Set the value of expYears
     *
     * @param expYears new value of expYears
     */
    public void setExpYears(int expYears) {
        this.expYears = expYears;
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

    public InterfaceController getController() {
        return controller;
    }

    public void setController(InterfaceController controller) {
        this.controller = controller;
    }
}
