/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.WorkerFacade;
import Entities.Worker;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Harvey
 */
@Named(value = "adminController")
@SessionScoped
public class AdminController implements Serializable {

    @EJB
    WorkerFacade workerFacade;
    private String nameInput;
    private String selectedName;
    protected HashMap<String, String> workermap;

    /**
     * Creates a new instance of InterfaceBeans
     */
    public AdminController() {
    }

    /**
     * *************************************************************************
     * Suggests employee names from the database when typing an employee name
     *
     * @param input
     * @return
     */
    public List<String> suggest(String input) {
        List<Worker> workers = workerFacade.findWorkerByName(input);
        List<String> names = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < workers.size(); i++) {
            
            names.add(workers.get(i).getFirstName().concat(" ")
                    .concat(workers.get(i).getLastName()));
            map.put(names.get(i), workers.get(i).getId());
        }
        workermap = map;
        return names;
    }

    public WorkerFacade getWorkerFacade() {
        return workerFacade;
    }

    public void setWorkerFacade(WorkerFacade workerFacade) {
        this.workerFacade = workerFacade;
    }

    public String getNameInput() {
        return nameInput;
    }

    public void setNameInput(String nameInput) {
        this.nameInput = nameInput;
    }

    public HashMap<String, String> getWorkermap() {
        return workermap;
    }

    public void setWorkermap(HashMap<String, String> workermap) {
        this.workermap = workermap;
    }
}
