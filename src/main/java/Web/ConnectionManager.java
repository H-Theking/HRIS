/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.AccountFacade;
import Entities.Account;
import static Web.UserManager.logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 *
 * @author Harvey
 */
@Named(value = "connectionManager")
@SessionScoped

public class ConnectionManager implements Serializable {

    @EJB
    private AccountFacade accountFacade;

    /**
     * Creates a new instance of ConnectionManager
     */
    public ConnectionManager() {
    }
    private String accountId;

    private String userName;

    private String password;

    private Account current;

    private String user;

    public void login() {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String encryptedPassword = encryptor.encryptPassword(password);
        System.out.println("Entered pass: " + encryptedPassword);
        List<Account> findAll = accountFacade.findAll();
        logger.log(Level.INFO, MessageFormat.format("Accounts Empty {0}", findAll.size()));
        for (Account find : findAll) {
            System.out.println("Username " + find.getUserName());
            System.out.println("Password " + find.getPassword());
            if (find.getUserName().equals(userName)) {
                user = find.getUserName();
            }
            if (find.getUserName().equals(userName)
                    && encryptor.checkPassword(password, encryptedPassword)) {

                current = find;
                System.out.println("User: Account");
//                session.setAttribute("workerName", find.getWorker().getFirstName()
//                        .concat(" ").concat(find.getWorker().getLastName()));
            }
        }

        if (current == null) {
            if (user != null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Nom d'utilisateur ou mot de passe inconnu", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Utilisateur inconnu", null));
            }
            throw new FacesException("Nom d'utilisateur ou mot de passe inconnu");
        }
//        else {
//            return "userhome?faces-redirect=true";
//        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return current != null;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
