/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.EmployeeFacade;
import EJB.LeaveFacade;
import Entities.Leave;
import Entities.Worker;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "leaveManager")
@RequestScoped
public class LeaveManager {
    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private LeaveFacade leaveFacade;
    
    private Worker worker;
    
    private String name;
    
    private Long leaveId;
    
    private String type;
    
    private Date startDate, endDate;
    
    private Leave[] selectedLeaves;
    
    private InterfaceController controller;
    
    private HashMap<String, String> workermap;
    /**
     * Creates a new instance of LeaveManager
     */
    public LeaveManager() {
        controller = new InterfaceController();
    }
    
    public void clearFields(){
        startDate = endDate = null;
        type = null;
        leaveId = null;
    }
    
    public void addLeave(){
        worker = employeeFacade.find(workermap.get(name));
        leaveFacade.addLeave(worker, type, startDate, endDate);
    }
    
    public void editLeave(){
        leaveFacade.editLeave(leaveId, worker, type, startDate, endDate);
    }
    
    public void cancelLeave(){
        for(Leave leave: selectedLeaves){
            leaveFacade.remove(leave);
        }
    }
    
    public void findLeave(AjaxBehaviorEvent event){
        UIParameter parameter = (UIParameter)event.getComponent().findComponent("leave");
        Leave leave = leaveFacade.find((Long)parameter.getValue());
        leaveId = leave.getId();
        type = leave.getType();
        startDate = leave.getStartDate();
        endDate = leave.getEndDate();
        worker = leave.getWorker();
    }
    
    public List<String> autoComplete(String input) {
        List<Worker> workers = employeeFacade.findWorkerByName(input);
        List<String> names = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < workers.size(); i++) {
            names.add(workers.get(i).getFirstName().concat(" ")
                    .concat(workers.get(i).getLastName()));
            map.put(names.get(i), workers.get(i).getId());
        }
        workermap = map;
        System.out.println("map" + map);
        return names;
    }
    
    public List<Leave> getLeaves(){
        return leaveFacade.findAll();
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Leave[] getSelectedLeaves() {
        return selectedLeaves;
    }

    public void setSelectedLeaves(Leave[] selectedLeaves) {
        this.selectedLeaves = selectedLeaves;
    }

    public InterfaceController getController() {
        return controller;
    }

    public void setController(InterfaceController controller) {
        this.controller = controller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}