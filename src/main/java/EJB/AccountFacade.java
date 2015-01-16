/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Account;
import Entities.Account.AccountType;
import Entities.Account.Status;
import Entities.Worker;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    public void createAccount(String workerId, AccountType type, String userName,
            String password, Status status){
        Worker worker = em.find(Worker.class, workerId);
        Account account =  new Account(type, userName, password, status);
        account.setWorker(worker);
        em.persist(account);
    }
    
    public void editAccount(String accoutId, AccountType type, String userName,
            String password, Status status){
        Account find = em.find(Account.class, accoutId);
        find.setUserName(userName);
        find.setPassword(password);
        find.setStatus(status);
        find.setType(type);
        em.merge(find);
//        em.createQuery("UPDATE Account a SET"
//                + "  a.type = " + type
//                + ", a.status = " + status
//                + ", a.userName = " + userName
//                + ", a.password = " + password
//                + " WHERE a.accountId = :id")
//                .setParameter("id", accoutId).executeUpdate();
    }
}
