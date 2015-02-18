/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Account;
import Entities.Organisation;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 *
 * @author Harvey
 */
@Singleton
@Startup
public class DefaultValues {
    @EJB
    private AccountFacade accountFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private OrganisationFacade organisationFacade;

    /**
     * Creates a new instance of DefaultValues
     */
    public DefaultValues() {
    }

    @PostConstruct
    public void createUser() {
        if (employeeFacade.findAll().isEmpty()) {
            employeeFacade.createWorker("0000", "admin", "admin", "admin");
            StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
            accountFacade.createAccount("0000", Account.AccountType.Administrateur, "admin", 
                    encryptor.encryptPassword("admin"), Account.Status.Actif);
            organisationFacade.create(new Organisation("Phoenix", "ABCD1234"));
        }
    }
    
    @PreDestroy
    public void deleteData() {
        
    }
}
