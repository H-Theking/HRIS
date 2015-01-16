/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.AccountFacade;
import Entities.Account;
import Entities.Account.AccountType;
import Entities.Account.Status;
import Entities.Worker;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
    private Account[] selectedAccounts;
    protected static final Logger logger = Logger.getGlobal();

    public AdminManager() {
        adminController = new AdminController();
        
    }

    public List<Account> getUsers() {
        List<Account> users = (List<Account>) accounts.findAll();
        return users;
    }

    public void addUser() {
        try {
            logger.log(java.util.logging.Level.OFF, "AdminManager.adduser()\n"
                    + "Creating new user with AccountId {0} and username {1}", 
                    new Object[]{accountId, userName});
            accountId = adminController.getWorkermap()//get the account correspnding to the 
                    .get(adminController.getNameInput());//name entered
            accounts.createAccount(accountId, type, userName, password, status);
            logger.log(java.util.logging.Level.OFF, "Created new user with "
                    + "AccountId {0} and username {1}", 
                    new Object[]{accountId, userName});

        } catch (EJBException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void deleteUser(){
        
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

    public Account[] getSelectedworkers() {
        return selectedAccounts;
    }

    public void setSelectedworkers(Account[] selectedworkers) {
        this.selectedAccounts = selectedworkers;
    }
    
}
