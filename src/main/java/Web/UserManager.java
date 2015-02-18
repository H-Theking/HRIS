/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.AccountFacade;
import EJB.EmployeeFacade;
import Entities.Account;
import Entities.Worker;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

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
    EmployeeFacade workerFacade;
    private String accountId;
    private Account.AccountType type;
    private List<String> types;
    private String employeeName;
    private String userName;
    private String password;
    private String passwordConfirm;
    private Account.Status status;
    private Account[] selectedAccounts;
    protected static final Logger logger = Logger.getGlobal();
    protected HashMap<String, String> workermap;

    /**
     * Creates a new instance of UserManager
     */
    public UserManager() {
        types = new ArrayList<>(2);
        types.add(Account.AccountType.Administrateur.toString());
        types.add(Account.AccountType.Superviseur.toString());
    }
    
    public UserManager(HashMap<String, String> map){
        map = workermap;
    }
    
    public void clearFields(AjaxBehaviorEvent event){
        type = null;
        userName = accountId = null;
    }

    public List<String> autoComplete(String input) {
        List<Worker> workers = workerFacade.findWorkerByName(input);
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
            try {
                System.out.println(getWorkermap());
                accountId = getWorkermap()//get the account correspnding to the 
                        .get(employeeName);//name entered
            } catch (NullPointerException e) {
                throw new NullPointerException("ID Not found");
            }
            logger.log(java.util.logging.Level.INFO, "Creating new user with "
                    + "AccountId {0}\n type: {1}\n username: {2}\n password: {3}\n"
                    + "status: {4}\n",
                    new Object[]{accountId, type, userName, password, status});
            accounts.createAccount(accountId, type, userName, password, status);
            logger.log(java.util.logging.Level.OFF, "Created new user with "
                    + "AccountId {0} and username {1}",
                    new Object[]{accountId, userName});

        } catch (EJBException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void deleteAccount(){
        for (Account selectedAccount: selectedAccounts) {
            accounts.remove(selectedAccount);
        }
    }
    
    public void findAccount(AjaxBehaviorEvent event){
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("user");
        String selectedUser = parameter.getValue().toString();
        Account find = accounts.find(selectedUser);
        userName = find.getUserName();
        password = find.getPassword();
        status = find.getStatus();
        type = find.getType();
        employeeName = find.getWorker().getFirstName().concat(" ")
                .concat(find.getWorker().getLastName());
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

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

}
