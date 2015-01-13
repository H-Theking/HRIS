/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.AccountFacade;
import EJB.JobFacade;
import EJB.LanguagesFacade;
import Entities.Account;
import Entities.Account.AccountType;
import Entities.Account.Status;
import Entities.Job;
import Entities.Languages;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
//import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "adminManager")
@SessionScoped
public class AdminManager implements Serializable {

    /**
     * Creates a new instance of AdminManager
     */
    @EJB
    AccountFacade accounts;
    AdminController adminController;
    private String accountId;
    private AccountType type;
    private String EmployeeName;
    private String userName;
    private String password;
    private String passwordConfirm;
    private Status status;

    public AdminManager() {
        adminController = new AdminController();
    }

    public List<Account> getUsers() {
        List<Account> users = (List<Account>) accounts.findAll();
        return users;
    }

    public void addUser() {
        try {
//            logger.log(java.util.logging.Level.OFF, "AdminManager.adduser()\n"
//                    + "Creating new user with AccountId {0} and username {1}", 
//                    new Object[]{accountId, userName});
            accountId = adminController.getWorkermap()//get the account correspnding to the 
                    .get(adminController.getNameInput());//name entered
            accounts.createAccount(accountId, type, userName, password, status);
//            logger.log(java.util.logging.Level.OFF, "Created new user with "
//                    + "AccountId {0} and username {1}", 
//                    new Object[]{accountId, userName});

        } catch (EJBException e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void matchPasswords() {
        if (!password.isEmpty()) {
            if (!password.equals(passwordConfirm)) {
                String msg = "Les mots de passes entres sont differents!";
                FacesMessage message;
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public AccountFacade getAccounts() {
        return accounts;
    }

    public void setAccounts(AccountFacade accounts) {
        this.accounts = accounts;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    /**
     * ************************************************************************
     * JOBS
     */
    @EJB
    JobFacade jobFacade;
    private String oldTitle;
    private String title;
    private String description;
    private String note;
    private Job[] selectedJobs;
    private List<Languages> languages;

    public void addJob() throws EJBException {
        jobFacade.addJob(title, description, note);
        title = description = note = null;
    }

    public void deleteJobs() {
        System.out.println("Selected: " + selectedJobs.length);
        for (Job selectedJob : selectedJobs) {
            jobFacade.remove(selectedJob);
        }
    }

    public void findJob(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("jobId");
            String jobTitle = param.getValue().toString();
            Job find = jobFacade.find(jobTitle);
            System.out.println("Found job :" + find);
            title = find.getJobTitle();
            description = find.getJobDescription();
            note = find.getNote();
            oldTitle = title;
        } catch (Exception e) {
        }
    }

    public void clearFields() {
        this.title = null;
        this.description = null;
        this.note = null;
    }

    public List<Job> getJobs() {
        return jobFacade.getJobs();
    }

    public void editJob() throws EJBException {
        jobFacade.editJob(oldTitle, title, description, note);
        title = description = note = oldTitle = null;
    }

    public JobFacade getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobFacade jobFacade) {
        this.jobFacade = jobFacade;
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
