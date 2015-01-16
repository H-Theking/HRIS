/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.JobFacade;
import Entities.Job;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "jobManager")
@SessionScoped
public class JobManager implements Serializable {

     @EJB
    JobFacade jobFacade;
    private String oldTitle;
    private String title;
    private String description;
    private String note;
    private Job[] selectedJobs;
    
    
    /**
     * Creates a new instance of JobManager
     */
    public JobManager() {
    }
    

    public void addJob() throws EJBException {
        if (oldTitle == null) {
           jobFacade.addJob(title, description, note); 
        }else{
            jobFacade.editJob(oldTitle, title, description, note);
        }
        clearFields();
    }

    public void deleteJobs() {
        System.out.println("Selected: " + selectedJobs.length);
        for (Job selectedJob : selectedJobs) {
            jobFacade.remove(selectedJob);
        }
    }

    public void findJob(AjaxBehaviorEvent event) {
        try {
            clearFields();
            UIParameter param = (UIParameter) event.getComponent().findComponent("jobId");
            String jobTitle = param.getValue().toString();
            Job find = jobFacade.find(jobTitle);
            System.out.println("Found job :" + find);
            oldTitle = title = find.getJobTitle();
            description = find.getJobDescription();
            note = find.getNote();
        } catch (Exception e) {
        }
    }

    public void clearFields() {
        this.title = null;
        this.description = null;
        this.note = null;
        this.oldTitle = null;
    }

    public List<Job> getJobs() {
        return jobFacade.getJobs();
    }

    public void editJob() throws EJBException {
        jobFacade.editJob(oldTitle, title, description, note);
        title = description = note = oldTitle = null;
    }

    public String getOldTitle() {
        return oldTitle;
    }

    public void setOldTitle(String oldTitle) {
        this.oldTitle = oldTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Job[] getSelectedJobs() {
        return selectedJobs;
    }

    public void setSelectedJobs(Job[] selectedJobs) {
        this.selectedJobs = selectedJobs;
    }
    
    
}
