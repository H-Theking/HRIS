/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.EmployeeFacade;
import EJB.WorkExperienceFacade;
import Entities.WorkExperience;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "qualifications")
@SessionScoped
public class Experience implements Serializable {
    
    private InterfaceController controller;
    @EJB
    private WorkExperienceFacade workExperienceFacade;

    @EJB
    private EmployeeFacade employeeFacade;

    private WorkExperience experience;

    /**
     * Creates a new instance of Qualifications
     */
    public Experience() {
        controller = new InterfaceController();
    }

    public void clearFields() {
        company = null;
        jobTitle = null;
        startdate = null;
        endDate = null;
        comment = null;
    }

    public void addWorkExperience() {
        workExperienceFacade.addWorkExperience(workerId, company, jobTitle, startdate, endDate, comment);
    }

    public void editExperience() {
        workExperienceFacade.editExperience(experience, company, jobTitle, startdate, endDate, comment);
    } 

    public void removeWorkExperience() {
        for (WorkExperience exp : selectedExps) {
            workExperienceFacade.remove(exp);
        }
    }

    public void findExperience(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("xp");
        experience = (WorkExperience) parameter.getValue();
        comment = experience.getComment();
        company = experience.getCompany();
        jobTitle = experience.getJobtitle();
        startdate = experience.getStartDate();
        endDate = experience.getEndDate();
    }

    public void getEmployee(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("worker");
        workerId = parameter.getValue().toString();
    }
    
    public List<WorkExperience> getFormerJobs(){
        return workExperienceFacade.findExperiences(workerId);
    }

    public void addSkill() {

    }

    public void removeSkill() {

    }

    public void addLanguage() {

    }

    public void removeLanbuage() {

    }

    private String company;

    private String jobTitle;

    private Date startdate;

    private Date endDate;

    private String comment;

    private String workerId;

    private WorkExperience[] selectedExps;

    private Long expId;

    /**
     * Get the value of expId
     *
     * @return the value of expId
     */
    public Long getExpId() {
        return expId;
    }

    /**
     * Set the value of expId
     *
     * @param expId new value of expId
     */
    public void setExpId(Long expId) {
        this.expId = expId;
    }

    /**
     * Get the value of selectedExps
     *
     * @return the value of selectedExps
     */
    public WorkExperience[] getSelectedExps() {
        return selectedExps;
    }

    /**
     * Set the value of selectedExps
     *
     * @param selectedExps new value of selectedExps
     */
    public void setSelectedExps(WorkExperience[] selectedExps) {
        this.selectedExps = selectedExps;
    }

    /**
     * Get the value of workerId
     *
     * @return the value of workerId
     */
    public String getWorkerId() {
        return workerId;
    }

    /**
     * Set the value of workerId
     *
     * @param workerId new value of workerId
     */
    public void setWorkerId(String workerId) {
        this.workerId = workerId;
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
     * Get the value of startdate
     *
     * @return the value of startdate
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * Set the value of startdate
     *
     * @param startdate new value of startdate
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * Get the value of jobTitle
     *
     * @return the value of jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Set the value of jobTitle
     *
     * @param jobTitle new value of jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Get the value of company
     *
     * @return the value of company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the value of company
     *
     * @param company new value of company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    public WorkExperience getExperience() {
        return experience;
    }

    public void setExperience(WorkExperience experience) {
        this.experience = experience;
    }

    public InterfaceController getController() {
        return controller;
    }

    public void setController(InterfaceController controller) {
        this.controller = controller;
    }
}
