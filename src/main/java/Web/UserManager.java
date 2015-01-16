/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.AccountFacade;
import EJB.WorkerFacade;
import Entities.Account;
import Entities.Worker;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Harvey
 */
@Named(value = "userManager")
@SessionScoped
public class UserManager implements Serializable {

    @EJB
    AccountFacade accounts;
    @EJB
    WorkerFacade workerFacade;
    private String accountId;
    private Account.AccountType type;
    private String employeeName;
    private String userName;
    private String password;
    private String passwordConfirm;
    private Account.Status status;
    private Account[] selectedAccounts;
    protected static final Logger logger = Logger.getGlobal();
    private HashMap<String, String> workermap;

    /**
     * Creates a new instance of UserManager
     */
    public UserManager() {
    }

    public List<String> suggest() {
        List<Worker> workers = workerFacade.findWorkerByName(employeeName);
        List<String> names = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < workers.size(); i++) {
            names.add(workers.get(i).getFirstName().concat(" ")
                    .concat(workers.get(i).getLastName()));
            map.put(names.get(i), workers.get(i).getId());
        }
        workermap = map;
        System.out.println(map);
        return names;
    }

    public List<Account> getUsers() {
        List<Account> users = (List<Account>) accounts.findAll();
        return users;
    }

    public void addUser() {
        try {
            logger.log(java.util.logging.Level.OFF, "AdminManager.adduser()\n"
                    + "Creating new user for employee {0} and username {1}",
                    new Object[]{employeeName, userName});
//            if (adminController.getNames().contains(employeeName)) {
//                logger.log(Level.INFO, "{0} found", new Object[]{employeeName});
//            } else {
//                logger.log(Level.WARNING, "{0} not found",  new Object[]{employeeName});
//            }
            try {
                System.out.println(getWorkermap());
                accountId = getWorkermap()//get the account correspnding to the 
                        .get(employeeName);//name entered
            } catch (NullPointerException e) {
                throw new NullPointerException("ID Not found");
            }

            accounts.createAccount(accountId, type, userName, password, status);
            logger.log(java.util.logging.Level.OFF, "Created new user with "
                    + "AccountId {0} and username {1}",
                    new Object[]{accountId, userName});

        } catch (EJBException e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void deleteUser() {

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

    public Account.AccountType getType() {
        return type;
    }

    public void setType(Account.AccountType type) {
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

    public Account.Status getStatus() {
        return status;
    }

    public void setStatus(Account.Status status) {
        this.status = status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.employeeName = EmployeeName;
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

    public Account[] getSelectedAccounts() {
        return selectedAccounts;
    }

    public void setSelectedAccounts(Account[] selectedAccounts) {
        this.selectedAccounts = selectedAccounts;
    }

    public HashMap<String, String> getWorkermap() {
        return workermap;
    }

    public void setWorkermap(HashMap<String, String> workermap) {
        this.workermap = workermap;
    }

}
