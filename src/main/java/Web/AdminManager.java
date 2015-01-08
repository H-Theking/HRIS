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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
//import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;

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
    private String accountId;
    private AccountType type;
    private String EmployeeName; 
    private String userName;
    private String password;
    private String passwordConfirm;
    private Status status;

    public AdminManager() {
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
            accounts.createAccount(accountId, type, userName, password, status);
//            logger.log(java.util.logging.Level.OFF, "Created new user with "
//                    + "AccountId {0} and username {1}", 
//                    new Object[]{accountId, userName});

        } catch (EJBException e) {
            throw new EJBException(e.getMessage());
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
    
    
}
