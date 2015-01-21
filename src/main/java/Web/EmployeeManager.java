/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.WorkerAndJobFacade;
import EJB.EmployeeFacade;
import Entities.Account.Status;
import Entities.Worker;
import Entities.Worker.CivilStatus;
import Entities.Worker.Gender;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.AjaxBehaviorEvent;

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
    private EmployeeFacade workerFacade;
    private static final Logger logger = Logger.getLogger("SampleApp.Web.EmployeeManager");
    private List<Worker> workers;

    private Worker[] selectedWorkers;
    private String id;
    private String userName;
    private String password;
    private String confirmPassword;
    private Status status;
    private List<Gender> genderList;
    private List<CivilStatus> statusList;
    ContactsManager manager;
    
    public EmployeeManager() {
        manager = new ContactsManager();
        genderList = new ArrayList<>(2);
        genderList.add(Gender.Homme);
        genderList.add(Gender.Femme);
        
        statusList = new ArrayList<>(2);
        statusList.add(CivilStatus.Célibataire);
        statusList.add(CivilStatus.Marié);
    }
    

    /**
     * 
     * getEmployee method returns a list of employees stored in the database.
     * 
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
            workerFacade.createWorker(id, firstName, middleNames, lastName);
            id = null;
            firstName = null;
            middleNames = null;
            lastName = null;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
    /***************************************************************************
     * Finds an employee whose name was clicked on on table of employees based 
     * on the id returned from the page
     * @param event 
     */
    private String firstName;
    private String middleNames;
    private String lastName;
    private String identity;
    private Date birthDate;
    private String placeOfBirth;
    private Gender gender;
    private CivilStatus civilStatus;
    private String nationality;
    private int numberOfchildren;
    
    public void findEmployee(AjaxBehaviorEvent event){
        UIParameter param = (UIParameter) event.getComponent().findComponent("worker");
        String wid = param.getValue().toString();
        Worker worker = workerFacade.find(wid);
        identity = manager.employeeId =  worker.getId();
        firstName = worker.getFirstName();
        middleNames = worker.getMiddlenames();
        lastName = worker.getLastName();
//        DateFormat df = DateFormat.getDateInstance();
//        birthDate = df.parse(worker.getBirthDate());
        birthDate = worker.getBirthDate();
        placeOfBirth = worker.getPlaceOfBirth();
        gender = worker.getGender();
        civilStatus = worker.getCivilStatus();
        nationality = worker.getNationality();
        numberOfchildren = worker.getNumberOfChildren();
        System.out.println("BirthDate: " + birthDate);
    }
    
    public void editEmployeeDetails(){
        System.out.println(birthDate);
        Worker find = workerFacade.find(identity);
        find.setFirstName(firstName);
        find.setMiddlenames(middleNames);
        find.setLastName(lastName);
        find.setBirthDate(birthDate);
        find.setPlaceOfBirth(placeOfBirth);
        find.setCivilStatus(civilStatus);
        find.setGender(gender);
        find.setNationality(nationality);
        find.setNumberOfChildren(numberOfchildren);
        workerFacade.edit(find);
        logger.log(Level.INFO, "Updated employee {0} - {1} {2}\'s details", 
                new Object[]{identity, firstName, lastName});
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(String middleNames) {
        this.middleNames = middleNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public CivilStatus getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(CivilStatus civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getNumberOfchildren() {
        return numberOfchildren;
    }

    public void setNumberOfchildren(int numberOfchildren) {
        this.numberOfchildren = numberOfchildren;
    }
    
    /**************************************************************************
     * 
     * @return 
     */
    public Worker[] getSelectedWorkers() {
        return selectedWorkers;
    }

    public void setSelectedWorkers(Worker[] selectedWorkers) {
        this.selectedWorkers = selectedWorkers;
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

    public List<Gender> getGenderList() {
        return genderList;
    }

    public void setGenderList(List<Gender> genderList) {
        this.genderList = genderList;
    }

    public List<CivilStatus> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<CivilStatus> statusList) {
        this.statusList = statusList;
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
}
