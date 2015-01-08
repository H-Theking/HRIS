/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.WorkerAndJobFacade;
import EJB.WorkerFacade;
import Entities.Account;
import Entities.Account.Status;
import Entities.EmployeeHasJob;
import Entities.Worker;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Harvey
 */
@ManagedBean
@SessionScoped
public class EmployeeManager implements Serializable {

    @EJB
    private WorkerAndJobFacade jobFacade;
    @EJB
    private WorkerFacade workerFacade;
    private static final Logger logger = Logger.getLogger("SampleApp.Web.EmployeeManager");
    private List<Worker> workers;

    private Worker[] selectedWorkers;
    private String fName;
    private String mName;
    private String lName;
    private String id;
    private String userName;
    private String password;
    private String confirmPassword;
    private Status status;

    /**
     * *************************************************************************
     * getEmployee method returns a list of employees stored in the database.
     * *************************************************************************
     * @return
     */
    public List<Worker> getEmployees() {
        try {
            return this.workerFacade.getWorkers();
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    /**
     * *************************************************************************
     * Adds an employee to the database. Required field are the first middle and
     * last names and the employee's id.
     */
    public void addEmployee() {
        try {
            workerFacade.createWorker(id, fName, mName, lName);
            id = null;
            fName = null;
            mName = null;
            lName = null;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

//    try {
//
//        } catch (Exception ex) {
//            throw new EJBException(ex.getMessage());
//        }
    public Worker[] getSelectedWorkers() {
        return selectedWorkers;
    }

    public void setSelectedWorkers(Worker[] selectedWorkers) {
        this.selectedWorkers = selectedWorkers;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//     public List<Worker> getWorkers() {
//        try {
//            this.workers = jobFacade.getWorkers();
//        } catch (Exception e) {
//            logger.warning("Couldn't get workers.");
//        }
//        return workers;
//    }
//
//    public List<EmployeeHasJob> getData() {
//        try {
//            this.employees = workerFacade.getData();
////            logger.log(Level.INFO, " Size {0}", new Object[]{employees.size()});
////            for (EmployeeHasJob employee : employees) {
////                logger.log(Level.INFO, "ID: {0} Fname: {1}",
////                        new Object[]{employee.getEmployeeId(), employee.getWorker().getFirstName()});
////            }
//        } catch (Exception e) {
////            logger.warning("Couldn't get employeeData.");
//        }
//        return employees;
//    }
//
////    public void removeJob(ActionEvent event){
////        try{
////            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteWorkerId");
////            String id = param.getValue().toString();
////            jobFacade.removeJob(id);
////             logger.log(Level.INFO, "Removed  {0}.", id);
////        }catch(NumberFormatException e){
////            
////        }
////    }
//    public void removeWorker(ActionEvent event) {
//        try {
//            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteWorkerId");
//            String id = param.getValue().toString();
//            jobFacade.removeWorker(id);
//            logger.log(Level.INFO, "Removed  {0}.", id);
//        } catch (Exception e) {
//        }
//    }
//
//    public void submit() {
//        try {
//            logger.log(Level.INFO, "WorkerId {0} firstname {1}", new Object[]{newWorkerId, newFirstName});
//            jobFacade.createWorker(newWorkerId, newFirstName, newlastName, newbirthDate);
//
//            logger.log(Level.INFO, "Created new woker with order ID {0}, name {1}  "
//                    + "{2}, and birthdate {3}.",
//                    new Object[]{newWorkerId, newFirstName, newlastName, newbirthDate});
//            this.newWorkerId = null;
//            this.newFirstName = null;
//            this.newlastName = null;
//            this.newbirthDate = null;
//        } catch (Exception e) {
//            logger.warning("Problem creating worker in submit.");
//        }
//    }
//
//    public void addWorker() {
//        try {
//            logger.log(Level.INFO, "EmployeeManager.addWorker()\n"
//                    + "WorkerId {0} firstname {1}", new Object[]{newWorkerId, newFirstName});
//            workerFacade.createWorker(newWorkerId, newFirstName, newMiddleNames, newlastName);
//
//            this.newWorkerId = null;
//            this.newFirstName = null;
//            this.newlastName = null;
//            this.newMiddleNames = null;
//        } catch (EJBException e) {
//            throw new EJBException(e.getMessage());
//        }
//    }
//
////    public void findWorker() {
////        try {
////            logger.log(Level.INFO, "WorkerId {0} firstname {1}", new Object[]{newWorkerId, newFirstName});
////            this.findWorkerTableDisabled = true;
////            this.workerSearchResults = jobFacade.findWorkerByName(workerFname);
////            logger.log(Level.INFO, "Found {0} worker(s) using the search string {1}.",
////                    new Object[]{workerSearchResults.size(), workerFname});
////        } catch (Exception e) {
////            logger.warning("Problem calling JobFacade.findWorkerByName from findWorker");
////        }
////    }
//
//    public List<Worker> findWorkerByName() {
//        return null;
//    }
//
//    public String getCurrentWorker() {
//        return currentWorker;
//    }
//
//    public void setCurrentWorker(String currentWorker) {
//        this.currentWorker = currentWorker;
//    }
//
//    public String getNewWorkerId() {
//        return newWorkerId;
//    }
//
//    public void setNewWorkerId(String newWorkerId) {
//        this.newWorkerId = newWorkerId;
//    }
//
//    public String getNewFirstName() {
//        return newFirstName;
//    }
//
//    public void setNewFirstName(String newFirstName) {
//        this.newFirstName = newFirstName;
//    }
//
//    public String getNewMiddleNames() {
//        return newMiddleNames;
//    }
//
//    public void setNewMiddleNames(String newMiddleNames) {
//        this.newMiddleNames = newMiddleNames;
//    }
//
//    public String getNewlastName() {
//        return newlastName;
//    }
//
//    public void setNewlastName(String newlastName) {
//        this.newlastName = newlastName;
//    }
//
//    public Date getNewbirthDate() {
//        return newbirthDate;
//    }
//
//    public void setNewbirthDate(Date newbirthDate) {
//        this.newbirthDate = newbirthDate;
//    }
//
//    public String getWorkerFname() {
//        return workerFname;
//    }
//
//    public void setWorkerFname(String workerFname) {
//        this.workerFname = workerFname;
//    }
//
//    public List<String> getWorkerSearchResults() {
//        return workerSearchResults;
//    }
//
//    public void setWorkerSearchResults(List<String> workerSearchResults) {
//        this.workerSearchResults = workerSearchResults;
//    }
//
//    public Boolean getFindWorkerTableDisabled() {
//        return findWorkerTableDisabled;
//    }
//
//    public void setFindWorkerTableDisabled(Boolean findWorkerTableDisabled) {
//        this.findWorkerTableDisabled = findWorkerTableDisabled;
//    }
//
//    public WorkerAndJobFacade getJobFacade() {
//        return jobFacade;
//    }
//
//    public void setJobFacade(WorkerAndJobFacade jobFacade) {
//        this.jobFacade = jobFacade;
//    }
//
//    /**
//     * ************************************************
//     *
//     * Getters and setters for search fields
//     *
//     * @return
//     */
//  
//
//    public List<EmployeeHasJob> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<EmployeeHasJob> employees) {
//        this.employees = employees;
//    }
//
}
