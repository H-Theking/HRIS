/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Harvey
 */
@Entity
public class Account implements Serializable {
    public enum AccountType {

        Administrateur,
        Superviseur,
        Employee
    }
    
    public enum Status{
        Actif,
        Deactive
    }

    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     FIELDS
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @Id
    @Column(name = "ACCOUNTID")
    private String accountId;
    private AccountType type;
    private String userName;
    private String password;
    private Status status;
    
    /*----------------------------------------------------------------
     ----------------------------------------------------------------
     RELATIONAL MAPPING
     ----------------------------------------------------------------
     ----------------------------------------------------------------*/
    @OneToOne
    @JoinColumn(name = "ACCOUNTID", insertable = false, updatable = false)
    private Worker worker;
    /*---------------------------------------------------------------
     ----------------------------------------------------------------
     CONSTRUCTORS
     ----------------------------------------------------------------
     ----------------------------------------------------------------
     -------*/
    public Account() {
    }

    public Account(AccountType type, String userName, String password, Status status) {
        this.type = type;
        this.userName = userName;
        this.password = password;
        this.status = status;
    }

    /*---------------------------------------------------------
    ----------------------------------------------------------------
    GETTERS AND SETTERS
    ----------------------------------------------------------------
    ----------------------------------------------------------------
    -------*/
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

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the accountId fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        return !((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId)));
    }

    @Override
    public String toString() {
        return "Entities.Account[ id=" + accountId + " ]";
    }

}
