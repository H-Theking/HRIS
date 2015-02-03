/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.EmployeeHasEducationFacade;
import Entities.Education;
import Entities.EmployeeHasEducation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.Year;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "empEdu")
@SessionScoped
public class EmpEdu implements Serializable {

    InterfaceController controller;
    @EJB
    private EmployeeHasEducationFacade employeeHasEducationFacade;

    private String workerId;

    /**
     * Creates a new instance of EmpEdu
     */
    public EmpEdu() {
        controller = new InterfaceController();
    }

    public void clearFields() {
        level = null;
        school = specialty = null;
        year = null;
        score = null;
        startDate = endDate = null;
        degreeId = null;
    }

    public void addDegree() {
        employeeHasEducationFacade.addDegree(workerId, level, school, year,
                specialty, score, startDate, endDate);
    }

    public void editdegreee() {
        employeeHasEducationFacade.editDegree(degreeId, level, school, year,
                specialty, score, startDate, endDate);
    }

    public void removeDegree() {
        for (EmployeeHasEducation education : selectedDegrees) {
            employeeHasEducationFacade.remove(education);
        }
    }

    public List<EmployeeHasEducation> getDegrees() {
        return employeeHasEducationFacade.degreeList(workerId);
    }

    public void findDegree(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("degree");
        EmployeeHasEducation degree = (EmployeeHasEducation) parameter.getValue();
        level = degree.getDegree();
        school = degree.getInstitute();
        specialty = degree.getSpecialty();
        year = degree.getGradYear();
        score = degree.getScore();
        startDate = degree.getStartDate();
        endDate = degree.getEndDate();
    }

    public void getEmployee(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("worker");
        workerId = parameter.getValue().toString();
    }

    private Education level;

    private String school;

    private String specialty;

    private Year year;

    private String score;

    private Date startDate;

    private Date endDate;

    private Long degreeId;

    private EmployeeHasEducation[] selectedDegrees;

    /**
     * Get the value of selectedDegrees
     *
     * @return the value of selectedDegrees
     */
    public EmployeeHasEducation[] getSelectedDegrees() {
        return selectedDegrees;
    }

    /**
     * Set the value of selectedDegrees
     *
     * @param selectedDegrees new value of selectedDegrees
     */
    public void setSelectedDegrees(EmployeeHasEducation[] selectedDegrees) {
        this.selectedDegrees = selectedDegrees;
    }

    /**
     * Get the value of degreeId
     *
     * @return the value of degreeId
     */
    public Long getDegreeId() {
        return degreeId;
    }

    /**
     * Set the value of degreeId
     *
     * @param degreeId new value of degreeId
     */
    public void setDegreeId(Long degreeId) {
        this.degreeId = degreeId;
    }

    /**
     * Get the value of endDate
     *
     * @return the value of endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the value of endDate
     *
     * @param endDate new value of endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Get the value of startDate
     *
     * @return the value of startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the value of startDate
     *
     * @param startDate new value of startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the value of year
     *
     * @return the value of year
     */
    public Year getYear() {
        return year;
    }

    /**
     * Set the value of year
     *
     * @param year new value of year
     */
    public void setYear(Year year) {
        this.year = year;
    }

    /**
     * Get the value of score
     *
     * @return the value private String score;of score
     */
    public String getScore() {
        return score;
    }

    /**
     * Set the value of score
     *
     * @param score new value of score
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * Get the value of specialty
     *
     * @return the value of specialty
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Set the value of specialty
     *
     * @param specialty new value of specialty
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * Get the value of school
     *
     * @return the value of school
     */
    public String getSchool() {
        return school;
    }

    /**
     * Set the value of school
     *
     * @param school new value of school
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Get the value of level
     *
     * @return the value of level
     */
    public Education getLevel() {
        return level;
    }

    /**
     * Set the value of level
     *
     * @param level new value of level
     */
    public void setLevel(Education level) {
        this.level = level;
    }

    public InterfaceController getController() {
        return controller;
    }

    public void setController(InterfaceController controller) {
        this.controller = controller;
    }

}
